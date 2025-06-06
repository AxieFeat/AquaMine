package net.aquamine.api.event.player.interact

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.Hand

/**
 * Called when a player interacts with an entity as a whole.
 *
 * This is used for interactions where it only matters that an entity was
 * interacted with, not where on the entity the interaction occurred.
 */
interface PlayerInteractWithEntityEvent : PlayerInteractEvent {

    /**
     * The entity that was interacted with.
     */
    val target: Entity

    /**
     * The hand that the player used to interact with the target.
     */
    val hand: Hand
}
