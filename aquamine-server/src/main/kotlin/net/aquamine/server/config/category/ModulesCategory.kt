package net.aquamine.server.config.category

import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Comment

@ConfigSerializable
@JvmRecord
data class ModulesCategory(
    @Comment("All the modules that should be enabled.")
    val enabled: Set<String> = emptySet()
)
