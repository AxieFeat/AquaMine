package net.aquamine.server.plugin.loader

import com.google.inject.Module
import net.aquamine.api.event.Event
import net.aquamine.api.event.EventNode
import net.aquamine.api.plugin.PluginContainer
import net.aquamine.api.plugin.PluginDescription

interface PluginSource {

    fun loadDescriptions(): Collection<PluginDescription>

    fun loadPlugin(candidate: PluginDescription): PluginDescription

    fun createPluginContainer(description: PluginDescription): PluginContainer

    fun createModule(container: PluginContainer): Module

    fun createPlugin(container: PluginContainer, vararg modules: Module)

    fun createEventNode(container: PluginContainer): EventNode<Event> {
        return EventNode.all(container.description.id)
    }

    fun onPluginLoaded(container: PluginContainer) {
        // Do nothing by default
    }

    fun onPluginsLoaded(containers: Collection<PluginContainer>) {
        // Do nothing by default
    }
}
