package net.aquamine.api.block

import net.aquamine.api.util.Vec3i

/**
 * Something that contains blocks.
 *
 * The default value that will be returned instead of null if no block is
 * found is the block state representing air.
 */
interface BlockContainer {

    /**
     * Gets the block at the given [x], [y], and [z] coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @return the block at the given coordinates
     */
    fun getBlock(x: Int, y: Int, z: Int): BlockState

    /**
     * Gets the block at the given [position].
     *
     * @param position the position
     * @return the block at the given position
     */
    fun getBlock(position: Vec3i): BlockState
}
