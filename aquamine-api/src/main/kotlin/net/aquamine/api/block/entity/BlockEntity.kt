package net.aquamine.api.block.entity

import net.aquamine.api.block.Block
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.World

/**
 * A block entity is an entity that exists as a companion to a block, so that
 * block can store information that would violate the functionality of blocks
 * on their own.
 *
 * Despite the name, however, these do not behave anything like regular
 * entities. They do not exist to the end user, cannot be seen, and
 * only exist to hold data that blocks cannot.
 *
 * These used to be known as tile entities, for all of you folks who remember
 * those days.
 */
interface BlockEntity {

    /**
     * The type of this block entity.
     */
    val type: BlockEntityType<*>

    /**
     * The world this block entity is in.
     */
    val world: World

    /**
     * The block that this entity is bound to.
     */
    val block: Block

    /**
     * The position of this block entity.
     *
     * This will be identical to the position of the associated block.
     */
    val position: Vec3i

}
