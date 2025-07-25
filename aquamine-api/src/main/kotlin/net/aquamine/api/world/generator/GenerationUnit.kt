package net.aquamine.api.world.generator

import net.aquamine.api.util.BoundingBox
import net.aquamine.api.util.Vec3i
import java.util.function.Consumer

/**
 * Represents an area that can be generated.
 *
 * In default area is all the world.
 */
interface GenerationUnit {

    /**
     * This unit's modifier, used to place blocks and biomes within this unit.
     */
    val modifier: UnitModifier

    /**
     * This AABB allows you to specify an area that can be modified using [modifier].
     * The coordinate values here must be multiples of 16.
     *
     * By default, if this is the root [GenerationUnit] the area will be all the world,
     * i.e., coordinates from (`-30.000.000`, `-64`, `-30.000.000`) to (`30.000.000`, `319`, `30.000.000`).
     */
    val area: BoundingBox

    /**
     * Creates a fork of this unit with new area.
     *
     * @param box Box of new area.
     *
     * @return The fork.
     */
    fun fork(box: BoundingBox): GenerationUnit

    /**
     * Creates a fork of this unit with new area.
     *
     * @param start The start of the fork.
     * @param end The end of the fork.
     *
     * @return The fork.
     */
    fun fork(start: Vec3i, end: Vec3i): GenerationUnit = fork(BoundingBox(start, end))

    /**
     * Automatically creates a GenerationUnit based on coordinates that have been modified using [UnitModifier].
     *
     * @param consumer The consumer.
     */
    fun fork(consumer: Consumer<UnitModifier>)

}