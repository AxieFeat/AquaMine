package net.aquamine.api.event.player.interact

import net.aquamine.api.entity.Entity

/**
 * Called when a player attacks an entity.
 */
interface PlayerAttackEntityEvent : PlayerInteractEvent {

    /**
     * The entity that was interacted with.
     */
    val target: Entity
}
