package net.aquamine.api.event.player

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.event.annotation.PerformanceSensitive
import net.aquamine.api.event.type.DeniableEventWithResult
import net.aquamine.api.event.type.PlayerEvent
import net.aquamine.api.util.Position

/**
 * Called when a player moves.
 *
 * This event is called incredibly frequently, and so any processing should be
 * either incredibly fast or handled asynchronously. Even for a server with
 * one or two players, this event could be called up to one hundred times per
 * second, or even more.
 */
@PerformanceSensitive
interface PlayerMoveEvent : PlayerEvent, DeniableEventWithResult<PlayerMoveEvent.Result> {

    /**
     * The position of the player before they moved.
     */
    val oldPosition: Position

    /**
     * The position of the player after they moved.
     */
    val newPosition: Position

    /**
     * The result of a move event.
     *
     * This allows plugins to modify the position that players will move to.
     *
     * @property newPosition The new position to move the player to.
     */
    @JvmRecord
    @ImmutableType
    data class Result(val newPosition: Position)
}
