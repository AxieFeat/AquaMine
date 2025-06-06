package net.aquamine.api.block.entity

import net.aquamine.api.util.Vec3i

/**
 * Something that contains block entities.
 */
interface BlockEntityContainer {

    /**
     * Gets the block entity at the given [x], [y], and [z] coordinates, or
     * returns null if there is no block entity at the given [x], [y], and [z]
     * coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     *
     * @return The block entity at the coordinates, or null if not present.
     */
    fun getBlockEntity(x: Int, y: Int, z: Int): BlockEntity?

    /**
     * Gets the block entity at the given [position], or returns null if there
     * is no block entity at the given [position].
     *
     * @param position The position of the block entity.
     *
     * @return The block entity at the position, or null if not present.
     */
    fun getBlockEntity(position: Vec3i): BlockEntity? = getBlockEntity(position.x, position.y, position.z)
}
