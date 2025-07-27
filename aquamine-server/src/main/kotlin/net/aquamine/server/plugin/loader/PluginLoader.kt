package net.aquamine.server.plugin.loader

import com.google.inject.Module
import org.apache.logging.log4j.LogManager
import net.aquamine.api.event.Event
import net.aquamine.api.event.EventNode
import net.aquamine.api.plugin.PluginContainer
import net.aquamine.api.plugin.PluginDescription
import net.aquamine.server.AquaServer
import net.aquamine.server.plugin.AquaPluginManager
import net.aquamine.server.plugin.PluginDependencies
import net.aquamine.server.plugin.module.GlobalModule
import java.nio.file.Files
import java.nio.file.Path

class PluginLoader(private val pluginManager: AquaPluginManager, private val sources: List<PluginSource>) {

    fun loadPlugins(server: AquaServer) {
        val found = ArrayList<PluginDescription>()
        val sourceByPluginId = HashMap<String, PluginSource>()

        sources.forEach { source ->
            val descriptions = source.loadDescriptions()
            found.addAll(descriptions)
            descriptions.forEach { description -> sourceByPluginId.put(description.id, source) }
        }
        if (found.isEmpty()) return // no plugins

        val sortedPlugins = PluginDependencies.sortCandidates(found)
        val loadedPluginIds = HashSet<String>()
        val loadedPlugins = ArrayList<LoadedPlugin>()

        // Load the plugins!
        sortedPlugins.forEach pluginLoad@{ candidate ->
            candidate.dependencies.forEach {
                if (it.isOptional || loadedPluginIds.contains(it.id)) return@forEach
                LOGGER.error("Failed to load plugin ${candidate.id} due to missing dependency on plugin ${it.id}!")
                return@pluginLoad
            }

            val source = sourceByPluginId.get(candidate.id) ?: error("No source for candidate $candidate! This is a bug!")
            try {
                val description = source.loadPlugin(candidate)
                val container = source.createPluginContainer(description)
                val pluginModule = source.createModule(container)
                val eventNode = source.createEventNode(container)
                loadedPlugins.add(LoadedPlugin(container, pluginModule, eventNode))
                loadedPluginIds.add(description.id)
            } catch (exception: Exception) {
                LOGGER.error("Failed to create Guice module for plugin ${candidate.id}!", exception)
            }
        }

        val containers = loadedPlugins.map { it.container }
        val commonModule = GlobalModule(server, containers)

        loadedPlugins.forEach { (container, module, eventNode) ->
            val description = container.description
            val source = sourceByPluginId[description.id] ?: error("No source for container plugin ${description.id}! This is a bug!")

            try {
                source.createPlugin(container, module, commonModule)
            } catch (exception: Exception) {
                LOGGER.error("Error whilst attempting to load plugin ${description.id}!", exception)
                return@forEach
            }

            source.onPluginLoaded(container)
            pluginManager.registerPlugin(container, eventNode)
        }
        sources.forEach { source -> source.onPluginsLoaded(containers) }
    }

    @JvmRecord
    private data class LoadedPlugin(val container: PluginContainer, val module: Module, val eventNode: EventNode<Event>)

    companion object {

        private val LOGGER = LogManager.getLogger()

        @JvmStatic
        fun createDefault(server: AquaServer): PluginLoader {
            val sources = ArrayList<PluginSource>()
            createPluginsSource(Path.of("plugins"))?.let { sources.add(it) }
            return PluginLoader(server.pluginManager, sources)
        }

        @JvmStatic
        private fun createPluginsSource(path: Path): JarPluginSource? {
            if (!Files.exists(path)) {
                try {
                    Files.createDirectory(path)
                } catch (exception: Exception) {
                    LOGGER.warn("Failed to create the plugins directory! Plugins will not be loaded!", exception)
                    return null
                }
            }
            if (!Files.isDirectory(path)) {
                LOGGER.warn("Plugins path $path is not a directory! Plugins will not be loaded!")
                return null
            }
            return JarPluginSource(path)
        }
    }
}
