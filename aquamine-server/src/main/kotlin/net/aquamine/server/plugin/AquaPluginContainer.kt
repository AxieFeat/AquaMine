package net.aquamine.server.plugin

import net.aquamine.api.plugin.PluginContainer
import net.aquamine.api.plugin.PluginDescription

class AquaPluginContainer(override val description: PluginDescription, val isModule: Boolean) : PluginContainer {

    override var instance: Any? = null
}
