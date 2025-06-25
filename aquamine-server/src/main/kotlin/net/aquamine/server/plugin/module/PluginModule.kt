package net.aquamine.server.plugin.module

import com.google.inject.Scopes
import dev.misfitlabs.kotlinguice4.KotlinModule
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import net.aquamine.api.event.Event
import net.aquamine.api.event.EventNode
import net.aquamine.api.plugin.PluginContainer
import net.aquamine.api.plugin.PluginDescription
import net.aquamine.api.plugin.annotation.DataFolder
import net.aquamine.server.event.KryptonGlobalEventNode
import java.nio.file.Path

class PluginModule(
    private val container: PluginContainer,
    private val mainClass: Class<*>,
    private val basePath: Path
) : KotlinModule() {

    override fun configure() {
        bind(mainClass).`in`(Scopes.SINGLETON)
        bind<Logger>().toInstance(LogManager.getLogger(container.description.id))
        bind<Path>().annotatedWith<DataFolder>().toInstance(basePath.resolve(container.description.id))
        bind<PluginDescription>().toInstance(container.description)
        bind<PluginContainer>().toInstance(container)
        bindEventNode()
    }

    private fun bindEventNode() {
        val node = EventNode.all(container.description.id)
        bind<EventNode<Event>>().toInstance(node)
        PLUGINS_EVENT_NODE.addChild(node)
    }

    companion object {

        private val PLUGINS_EVENT_NODE = createPluginsEventNode()

        @JvmStatic
        private fun createPluginsEventNode(): EventNode<Event> {
            val node = EventNode.all("plugins")
            KryptonGlobalEventNode.addChild(node)
            return node
        }
    }
}
