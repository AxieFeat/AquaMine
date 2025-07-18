package net.aquamine.api.world.generator

import net.aquamine.api.block.BlockContainer
import net.aquamine.api.util.Vec3i
import java.util.function.Consumer

/**
 * Represents an area that can be generated.
 *
 * The size is guaranteed to be a multiple of 16 (section).
 */
interface GenerationUnit {

    /**
     * This unit's modifier, used to place blocks and biomes within this unit.
     *
     * @return The modifier.
     */
    fun modifier(): UnitModifier

    /**
     * The size of this unit in blocks.
     *
     * Guaranteed to be a multiple of 16.
     *
     * @return The size of this unit.
     */
    fun size(): Vec3i

    /**
     * The absolute start (min x, y, z) of this unit.
     *
     * @return The absolute start.
     */
    fun absoluteStart(): Vec3i

    /**
     * The absolute end (max x, y, z) of this unit.
     *
     * @return The absolute end.
     */
    fun absoluteEnd(): Vec3i

    /**
     * Creates a fork of this unit, which will be applied to the instance whenever possible.
     *
     * @param start The start of the fork.
     * @param end The end of the fork.
     *
     * @return The fork.
     */
    fun fork(start: Vec3i, end: Vec3i): GenerationUnit

    /**
     * Creates a fork of this unit depending on the blocks placed within the consumer.
     *
     * @param consumer The consumer.
     */
    fun fork(consumer: Consumer<BlockContainer>)

    /**
     * Divides this unit into the smallest independent units.
     *
     * @return An immutable list of independent units.
     */
    fun subdivide(): List<GenerationUnit> {
        return listOf(this)
    }
}