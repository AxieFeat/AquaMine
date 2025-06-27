package net.aquamine.server.plugin.server

import net.aquamine.api.plugin.PluginContainer
import net.aquamine.api.plugin.PluginDescription
import net.aquamine.server.plugin.AquaPluginDescription

/**
 * A plugin container representing the current server.
 *
 * This plugin container will **not** be registered to the plugin manager, and
 * it only exists for use with things that require plugin containers, such as
 * the services manager.
 */
object ServerPluginContainer : PluginContainer {

    override val description: PluginDescription
        get() = AquaPluginDescription.SERVER
    override val instance: Any?
        get() = null
}
