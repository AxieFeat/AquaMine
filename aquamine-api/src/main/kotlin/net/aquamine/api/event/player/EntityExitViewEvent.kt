package net.aquamine.api.event.player

import net.aquamine.api.entity.Entity
import net.aquamine.api.event.type.PlayerEvent

/**
 * Called when an entity goes out of view of a player.
 */
interface EntityExitViewEvent : PlayerEvent {

    /**
     * The entity that went out of view.
     */
    val entity: Entity
}
