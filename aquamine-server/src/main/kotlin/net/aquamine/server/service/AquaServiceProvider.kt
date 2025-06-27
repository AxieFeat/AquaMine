package net.aquamine.server.service

import net.aquamine.api.plugin.PluginContainer
import net.aquamine.api.service.ServiceProvider

@JvmRecord
data class AquaServiceProvider<T>(
    override val plugin: PluginContainer,
    override val type: Class<T>,
    override val service: T
) : ServiceProvider<T>
