package net.aquamine.server.config.category

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.TextColor
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Comment

@ConfigSerializable
@JvmRecord
data class StatusCategory(
    @Comment("If this server responds to status requests from clients.")
    val enabled: Boolean = true,
    @Comment("The message of the day. Supports legacy and hex codes (using &#).")
    val motd: TextComponent = DEFAULT_MOTD,
    @Comment("The upper limit of the player count. Any players that try to join when this is reached will be kicked.")
    val maxPlayers: Int = DEFAULT_MAX_PLAYERS
) {

    companion object {

        const val DEFAULT_MAX_PLAYERS: Int = 20
        private val DEFAULT_MOTD = Component.text("AquaMine is a Minecraft server written in Kotlin!", TextColor.color(128, 0, 255))
    }
}
