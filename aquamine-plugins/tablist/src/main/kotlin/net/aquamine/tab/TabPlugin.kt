package net.aquamine.tab

import net.aquamine.api.event.Listener
import net.aquamine.api.event.player.PlayerJoinEvent
import net.aquamine.api.event.server.ServerStopEvent
import net.kyori.adventure.text.Component

class TabPlugin {

    @Listener
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendPlayerListHeaderAndFooter(
            Component.text("123"),
            Component.text("321"),
        )
    }

    @Listener
    fun onStop(event: ServerStopEvent) {

    }

}