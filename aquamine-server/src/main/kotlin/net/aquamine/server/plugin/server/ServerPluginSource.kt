package net.aquamine.server.plugin.server

import com.google.common.collect.ImmutableList
import com.google.gson.stream.JsonReader
import com.google.inject.Module
import org.apache.logging.log4j.LogManager
import net.aquamine.api.plugin.PluginContainer
import net.aquamine.api.plugin.PluginDependency
import net.aquamine.api.plugin.PluginDescription
import net.aquamine.server.plugin.AquaPluginContainer
import net.aquamine.server.plugin.AquaPluginDependency
import net.aquamine.server.plugin.AquaPluginDescription
import net.aquamine.server.plugin.loader.PluginSource
import net.aquamine.server.plugin.module.PluginModule
import net.aquamine.server.util.NoSpread
import net.aquamine.processor.SerializedPluginDescription
import java.io.InputStreamReader
import java.net.URI
import java.nio.file.Path

class ServerPluginSource(
    private val moduleDiscoverer: ModuleDiscoverer,
    private val modulesDirectory: Path,
    private val modules: ServerModules
) : PluginSource {

    override fun loadDescriptions(): Collection<PluginDescription> {
        val moduleInfoFiles = moduleDiscoverer.discover()
        val result = ArrayList<PluginDescription>()
        moduleInfoFiles.forEach { uri ->
            val moduleName = getModuleNameFromUri(uri)
            if (!modules.isEnabled(moduleName)) return@forEach

            try {
                result.add(loadDescription(uri))
            } catch (exception: Exception) {
                LOGGER.error("Failed to load internal module $moduleName!", exception)
            }
        }
        if (result.isEmpty()) LOGGER.warn("No internal modules found on classpath.")
        return result
    }

    private fun loadDescription(source: URI): LoadedCandidateDescription {
        val serialized = findMetadata(source) ?: throw InvalidModuleException("Module ${getModuleNameFromUri(source)} has invalid metadata!")
        if (!serialized.id.matches(SerializedPluginDescription.ID_REGEX)) throw InvalidModuleException("Module ID ${serialized.id} is invalid!")
        return serializedToCandidate(serialized)
    }

    private fun findMetadata(uri: URI): SerializedPluginDescription? {
        val reader = JsonReader(InputStreamReader(uri.toURL().openStream()))
        return reader.use { SerializedPluginDescription.read(it) }
    }

    override fun loadPlugin(candidate: PluginDescription): PluginDescription {
        require(candidate is LoadedCandidateDescription) { "Description provided isn't a loaded module candidate!" }
        val mainClass = Thread.currentThread().contextClassLoader.loadClass(candidate.mainClass)
        return candidate.toFull(mainClass)
    }

    override fun createPluginContainer(description: PluginDescription): PluginContainer = AquaPluginContainer(description, true)

    override fun createModule(container: PluginContainer): Module {
        val description = container.description
        require(description is LoadedDescription) { "Description for provided container isn't compatible with this loader!" }
        return PluginModule(container, description.mainClass, modulesDirectory)
    }

    override fun createPlugin(container: PluginContainer, vararg modules: Module) {
        require(container is AquaPluginContainer) { "Container provided isn't compatible with this loader!" }
        val description = container.description
        require(description is LoadedDescription) { "Description provided isn't compatible with this loader!" }

        val injector = NoSpread.guiceCreateInjector(modules)
        val instance = requireNotNull(injector.getInstance(description.mainClass)) { "Got nothing from injector for module ${description.id}!" }

        container.instance = instance
    }

    override fun onPluginsLoaded(containers: Collection<PluginContainer>) {
        val result = StringBuilder()
        var i = 0
        modules.all.forEach { name ->
            result.append('\t')
            result.append("Module $name: ")
            if (modules.isEnabled(name)) {
                if (containers.any { it.description.id.startsWith(name) }) {
                    result.append("Loaded")
                } else {
                    result.append("Enabled not loaded, check for errors")
                }
            } else {
                result.append("Disabled")
            }
            if (i != modules.all.size - 1) result.append('\n')
            ++i
        }
        LOGGER.info("Server module status: \n$result")
    }

    private class LoadedCandidateDescription(
        from: SerializedPluginDescription,
        val mainClass: String
    ) : AquaPluginDescription(from.id, from.name, from.version, from.description, convertAuthors(from), convertDependencies(from), null) {

        fun toFull(mainClass: Class<*>): LoadedDescription = LoadedDescription(this, mainClass)

        companion object {

            @JvmStatic
            private fun convertAuthors(from: SerializedPluginDescription): List<String> = ImmutableList.copyOf(from.authors)

            @JvmStatic
            fun convertDependencies(from: SerializedPluginDescription): Collection<PluginDependency> {
                val result = ImmutableList.builder<PluginDependency>()
                from.dependencies.forEach { result.add(AquaPluginDependency(it.id, it.optional)) }
                return result.build()
            }
        }
    }

    private class LoadedDescription(
        from: LoadedCandidateDescription,
        val mainClass: Class<*>
    ) : AquaPluginDescription(from.id, from.name, from.version, from.description, from.authors, from.dependencies, null)

    companion object {

        private const val JSON_SUFFIX = ".json"
        private val LOGGER = LogManager.getLogger()

        @JvmStatic
        private fun getModuleNameFromUri(uri: URI): String {
            // If we are running in the IDE, path should be non-null, as the URI will just be a path and nothing else.
            // If we are running in a JAR, schemeSpecificPart will be a file:// location, and path will be null.
            val pathName = uri.path ?: uri.schemeSpecificPart
            val lastSeparator = pathName.lastIndexOf('/')

            val lastPart = pathName.substring(lastSeparator + 1)
            if (!lastPart.endsWith(JSON_SUFFIX)) error("Module metadata file should end with '$JSON_SUFFIX'! Was $lastPart.")

            val lengthOfModuleName = lastPart.length - JSON_SUFFIX.length
            return lastPart.substring(0, lengthOfModuleName)
        }

        @JvmStatic
        private fun serializedToCandidate(description: SerializedPluginDescription): LoadedCandidateDescription =
            LoadedCandidateDescription(description, description.main)
    }
}
