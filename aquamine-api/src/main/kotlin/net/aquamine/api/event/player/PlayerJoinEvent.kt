package net.aquamine.api.event.player

import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.text.Component
import net.aquamine.api.event.type.DeniableEventWithResult
import net.aquamine.api.event.type.PlayerEvent

/**
 * Called when a player logs in and a player object has been constructed for
 * them.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface PlayerJoinEvent : PlayerEvent, DeniableEventWithResult<PlayerJoinEvent.Result> {

    /**
     * If the player has joined the server before.
     */
    @get:JvmName("hasJoinedBefore")
    val hasJoinedBefore: Boolean

    /**
     * The result of a join event.
     */
    @JvmRecord
    @ImmutableType
    data class Result(
        /**
         * The custom join message to send, or null, if no custom message is to
         * be sent.
         *
         * Note: some implementations may still send a join message.
         */
        val message: Component?,
        /**
         * If the joining player has joined before.
         *
         * Implementations may choose to use this information to alter the join
         * message.
         */
        val hasJoinedBefore: Boolean
    )
}
