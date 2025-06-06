package net.aquamine.api.event.player

import net.aquamine.api.entity.Entity
import net.aquamine.api.event.type.PlayerEvent

/**
 * Called when an entity comes in to view of a player.
 */
interface EntityEnterViewEvent : PlayerEvent {

    /**
     * The entity that came in to view.
     */
    val entity: Entity
}
