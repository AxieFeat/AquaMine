package net.aquamine.api.event.player

import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.text.Component
import net.aquamine.api.event.type.DeniableEventWithResult
import net.aquamine.api.event.type.PlayerEvent

/**
 * Called when a player sends a chat message (not a command).
 */
interface PlayerChatEvent : PlayerEvent, DeniableEventWithResult<PlayerChatEvent.Result> {

    /**
     * The message that the player has sent.
     */
    val message: String

    /**
     * The result of a chat event.
     *
     * This allows you to modify the message sent by the player.
     * For example, you could replace bad words with asterisks.
     *
     * @property message The message that will be sent by the player.
     */
    @JvmRecord
    @ImmutableType
    data class Result(val message: Component)
}
