package net.aquamine.server.plugin

import net.aquamine.api.event.Event
import net.aquamine.api.event.EventNode
import net.aquamine.api.plugin.PluginContainer
import net.aquamine.api.plugin.PluginManager
import net.aquamine.server.event.AquaGlobalEventNode
import java.nio.file.Path
import java.util.Collections
import java.util.IdentityHashMap

class AquaPluginManager : PluginManager {

    private val pluginMap = LinkedHashMap<String, PluginContainer>()
    private val pluginInstances = IdentityHashMap<Any, PluginContainer>()
    private val parentEventNode = createPluginsEventNode()
    private val pluginEventNodes = HashMap<PluginContainer, EventNode<Event>>()

    override val plugins: Collection<PluginContainer> = Collections.unmodifiableCollection(pluginMap.values)

    private fun createPluginsEventNode(): EventNode<Event> {
        val node = EventNode.all("plugins")
        AquaGlobalEventNode.addChild(node)
        return node
    }

    fun registerPlugin(plugin: PluginContainer, eventNode: EventNode<Event>) {
        pluginMap.put(plugin.description.id, plugin)
        plugin.instance?.let { pluginInstances.put(it, plugin) }

        parentEventNode.addChild(eventNode)
        pluginEventNodes.put(plugin, eventNode)
    }

    fun getEventNode(plugin: PluginContainer): EventNode<Event> {
        return checkNotNull(pluginEventNodes.get(plugin)) { "No event node found for plugin $plugin! This is a bug!" }
    }

    override fun fromInstance(instance: Any): PluginContainer? {
        if (instance is PluginContainer) return instance
        return pluginInstances.get(instance)
    }

    override fun getPlugin(id: String): PluginContainer? = pluginMap.get(id)

    override fun isLoaded(id: String): Boolean = pluginMap.containsKey(id)

    override fun addToClasspath(plugin: Any, path: Path) {
        val container = requireNotNull(fromInstance(plugin)) { "Plugin is not loaded!" }
        val instance = requireNotNull(container.instance) { "Plugin has no instance!" }

        val loader = instance.javaClass.classLoader
        if (loader !is PluginClassLoader) throw UnsupportedOperationException("Operation is not supported for non-AquaMine plugins!")
        loader.addPath(path)
    }
}
