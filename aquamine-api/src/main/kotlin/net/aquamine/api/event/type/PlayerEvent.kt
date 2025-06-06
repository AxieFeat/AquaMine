package net.aquamine.api.event.type

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.Event

/**
 * An event that involves a player.
 */
interface PlayerEvent : Event {

    /**
     * The player involved in this event.
     */
    val player: Player
}
