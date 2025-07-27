package net.aquamine.api.world.generator

import net.aquamine.api.block.BlockContainer
import net.aquamine.api.block.BlockState
import net.aquamine.api.util.BoundingBox
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.biome.Biome

/**
 * Represents a modifier of the world. All coordinates here are absolute.
 *
 * If you try to change something outside the area of this unit, it will be ignored
 */
interface UnitModifier : BlockContainer {

    /**
     * Sets the block in area by absolute coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     *
     * @param block The block to set.
     */
    fun setBlock(x: Int, y: Int, z: Int, block: BlockState)

    /**
     * Sets the block in area by absolute coordinates.
     *
     * @param pos Position in the world.
     *
     * @param block The block to set.
     */
    fun setBlock(pos: Vec3i, block: BlockState) = setBlock(pos.x, pos.y, pos.z, block)

    /**
     * Fills the unit with the given block.
     *
     * @param block The block to fill.
     */
    fun fill(block: BlockState)

    /**
     * Fills the 3d rectangular area with the given block.
     *
     * @param area Area to fill.
     *
     * @param block The block to fill.
     */
    fun fill(area: BoundingBox, block: BlockState)

    /**
     * Fills the 3d rectangular area with the given block.
     *
     * @param start Start point.
     * @param end End point.
     *
     * @param block The block to fill.
     */
    fun fill(start: Vec3i, end: Vec3i, block: BlockState) = fill(BoundingBox(start, end), block)

    /**
     * Fills the 3d rectangular area with the given block.
     *
     * @param minHeight The minimum height of the area.
     * @param maxHeight The maximum height of the area.
     *
     * @param block The block to fill.
     */
    fun fillHeight(minHeight: Int, maxHeight: Int, block: BlockState)

    /**
     * Fills the 3d rectangular area with the given biome.
     *
     * @param biome The biome to fill.
     */
    fun fillBiome(biome: Biome)

}
