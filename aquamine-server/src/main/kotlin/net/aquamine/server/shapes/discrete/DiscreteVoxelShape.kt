package net.aquamine.server.shapes.discrete

import net.aquamine.api.util.Direction
import net.aquamine.server.util.math.AxisCycle

abstract class DiscreteVoxelShape protected constructor(val sizeX: Int, val sizeY: Int, val sizeZ: Int) {

    init {
        require(sizeX >= 0 && sizeY >= 0 && sizeZ >= 0) { "All sizes must be positive! X: $sizeX, Y: $sizeY, Z: $sizeZ" }
    }

    abstract fun isFull(x: Int, y: Int, z: Int): Boolean

    private fun isFull(cycle: AxisCycle, x: Int, y: Int, z: Int): Boolean =
        isFull(cycle.cycle(x, y, z, Direction.Axis.X), cycle.cycle(x, y, z, Direction.Axis.Y), cycle.cycle(x, y, z, Direction.Axis.Z))

    fun isFullWide(x: Int, y: Int, z: Int): Boolean {
        if (x >= 0 && y >= 0 && z >= 0) {
            if (x < sizeX && y < sizeY && z < sizeZ) return isFull(x, y, z)
            return false
        }
        return false
    }

    fun isFullWide(cycle: AxisCycle, x: Int, y: Int, z: Int): Boolean =
        isFullWide(cycle.cycle(x, y, x, Direction.Axis.X), cycle.cycle(x, y, z, Direction.Axis.Y), cycle.cycle(x, y, z, Direction.Axis.Z))

    abstract fun fill(x: Int, y: Int, z: Int)

    open fun isEmpty(): Boolean = AXIS_VALUES.any { firstFull(it) >= lastFull(it) }

    abstract fun firstFull(axis: Direction.Axis): Int

    abstract fun lastFull(axis: Direction.Axis): Int

    fun firstFull(axis: Direction.Axis, y: Int, z: Int): Int {
        val size = size(axis)
        if (y < 0 || z < 0) return size
        val forward = AxisCycle.FORWARD.cycle(axis)
        val backward = AxisCycle.BACKWARD.cycle(axis)
        if (y >= size(forward) || z >= size(backward)) return size
        val cycle = AxisCycle.between(Direction.Axis.X, axis)
        for (x in 0 until size) {
            if (isFull(cycle, x, y, z)) return x
        }
        return size
    }

    fun lastFull(axis: Direction.Axis, y: Int, z: Int): Int {
        if (y < 0 || z < 0) return 0
        val forward = AxisCycle.FORWARD.cycle(axis)
        val backward = AxisCycle.BACKWARD.cycle(axis)
        if (y >= size(forward) || z >= size(backward)) return 0
        val size = size(axis)
        val cycle = AxisCycle.between(Direction.Axis.X, axis)
        for (x in size - 1 downTo 0) {
            if (isFull(cycle, x, y, z)) return x + 1
        }
        return 0
    }

    fun size(axis: Direction.Axis): Int = axis.select(sizeX, sizeY, sizeZ)

    fun sizeX(): Int = size(Direction.Axis.X)

    fun sizeY(): Int = size(Direction.Axis.Y)

    fun sizeZ(): Int = size(Direction.Axis.Z)

    fun forAllBoxes(combine: Boolean, consumer: IntLineConsumer) {
        BitSetDiscreteVoxelShape.forAllBoxes(this, consumer, combine)
    }

    fun interface IntLineConsumer {

        fun consume(x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int)
    }

    companion object {

        private val AXIS_VALUES = Direction.Axis.values()
    }
}
