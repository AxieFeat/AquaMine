package net.aquamine.api.event.player.interact

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.Hand
import net.aquamine.api.util.Vec3d

/**
 * Called when a player interacts with a specific part of an entity.
 *
 * This is used for interactions that require knowledge of which part of an
 * entity was clicked, such as placing armor on to an armor stand.
 */
interface PlayerInteractAtEntityEvent : PlayerInteractEvent {

    /**
     * The entity that was interacted with.
     */
    val target: Entity

    /**
     * The hand the player used to interact with the target.
     */
    val hand: Hand

    /**
     * The position that the player clicked on the entity.
     */
    val clickedPosition: Vec3d
}
