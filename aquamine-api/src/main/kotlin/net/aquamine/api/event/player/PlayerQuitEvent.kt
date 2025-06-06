package net.aquamine.api.event.player

import net.kyori.adventure.text.Component
import net.aquamine.api.event.type.PlayerEvent

/**
 * Called when the connection to a player is lost.
 */
interface PlayerQuitEvent : PlayerEvent {

    /**
     * The message that will be sent to all other players on the server when
     * the player quits.
     *
     * Set this to null to send no message when the player quits.
     */
    var quitMessage: Component?
}
