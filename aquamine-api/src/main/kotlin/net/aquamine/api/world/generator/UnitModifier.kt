package net.aquamine.api.world.generator

import net.aquamine.api.block.Block
import net.aquamine.api.block.BlockContainer
import net.aquamine.api.registry.DynamicRegistryReference
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.biome.Biome

interface UnitModifier : BlockContainer {

    /**
     * Sets the block relative to the absolute position of the unit.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     * @param block The block to set.
     */
    fun setRelative(x: Int, y: Int, z: Int, block: Block)

    /**
     * Sets all blocks within the unit to the block given by the supplier.
     *
     * @param supplier The supplier of the block to set.
     */
    fun setAll(supplier: Supplier)

    /**
     * Sets all blocks within the unit to the block given by the supplier, relative to the absolute position of the unit.
     *
     * @param supplier The supplier of the block to set.
     */
    fun setAllRelative(supplier: Supplier)

    /**
     * Fills the unit with the given block.
     *
     * @param block The block to fill.
     */
    fun fill(block: Block)

    /**
     * Fills the 3d rectangular area with the given block.
     *
     * @param start The start (min) point of the area.
     * @param end The end (max) point of the area.
     * @param block The block to fill.
     */
    fun fill(start: Vec3i, end: Vec3i, block: Block)

    /**
     * Fills the 3d rectangular area with the given block.
     *
     * @param minHeight The minimum height of the area.
     * @param maxHeight The maximum height of the area.
     * @param block The block to fill.
     */
    fun fillHeight(minHeight: Int, maxHeight: Int, block: Block)

    /**
     * Fills the 3d rectangular area with the given biome.
     *
     * @param biome the biome to fill
     */
    fun fillBiome(biome: DynamicRegistryReference<Biome>)

    interface Supplier {
        fun get(x: Int, y: Int, z: Int): Block
    }

}