package net.aquamine.server.config.category

import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Comment

@ConfigSerializable
@JvmRecord
data class GeneratorCategory(
    @Comment("The world generation settings as a JSON string")
    val settings: String = "{}",
    @Comment("The seed for the world. Leave blank for random")
    val seed: String = "",
    @Comment("The type of world to generate")
    val type: String = "minecraft:normal",
    @Comment("If structures should be generated")
    val structures: Boolean = true
)
