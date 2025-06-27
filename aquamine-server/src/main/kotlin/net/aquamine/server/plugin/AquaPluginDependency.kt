package net.aquamine.server.plugin

import net.aquamine.api.plugin.PluginDependency

@JvmRecord
data class AquaPluginDependency(override val id: String, override val isOptional: Boolean) : PluginDependency
