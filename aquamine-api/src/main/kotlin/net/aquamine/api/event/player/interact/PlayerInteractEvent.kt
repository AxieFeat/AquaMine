package net.aquamine.api.event.player.interact

import net.aquamine.api.event.type.DeniableEvent
import net.aquamine.api.event.type.PlayerEvent

/**
 * The superclass for all events involving a player's interaction with
 * something in a world.
 */
sealed interface PlayerInteractEvent : PlayerEvent, DeniableEvent
