package net.aquamine.server.event.player

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.PlayerQuitEvent

class KryptonPlayerQuitEvent(override val player: Player) : PlayerQuitEvent {

    override var quitMessage: Component? = Component.translatable("multiplayer.player.left", NamedTextColor.YELLOW, Component.text(player.name))
}
