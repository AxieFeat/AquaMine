package net.aquamine.api.event.player.interact

import net.aquamine.api.block.BlockState
import net.aquamine.api.entity.Hand
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i

/**
 * Called when a player places a block.
 */
interface PlayerPlaceBlockEvent : PlayerInteractEvent {

    /**
     * The block that was placed.
     */
    val block: BlockState

    /**
     * The hand that the player used to place the block.
     */
    val hand: Hand

    /**
     * The position where the block was placed.
     */
    val position: Vec3i

    /**
     * The face of the block on which the block was placed.
     */
    val face: Direction

    /**
     * Whether the player's head is inside the block.
     */
    val isInsideBlock: Boolean
}
