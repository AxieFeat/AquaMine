package net.aquamine.server.config.category

import net.kyori.adventure.text.Component
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Comment

@ConfigSerializable
@JvmRecord
data class ResourcePackCategory(
    @Comment("The URI where the resource pack is located.")
    val uri: String = "",
    @Comment("The hash used to verify the downloaded resource pack is correct.")
    val hash: String = "",
    @Comment("If clients will be forced to accept the resource pack.")
    val forced: Boolean = false,
    @Comment("The prompt message clients will be sent when accepting the resource pack.")
    val prompt: Component? = null
)
