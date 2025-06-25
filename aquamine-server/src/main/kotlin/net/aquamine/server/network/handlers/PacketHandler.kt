package net.aquamine.server.network.handlers

import net.kyori.adventure.text.Component

/**
 * The base interface for packet handlers. This exists primarily to avoid
 * having one handler for all, which is what we used to have before this
 * system.
 */
sealed interface PacketHandler {

    fun onDisconnect(message: Component?) {
        // Do nothing by default, only the play and login handlers do something for this
    }
}
