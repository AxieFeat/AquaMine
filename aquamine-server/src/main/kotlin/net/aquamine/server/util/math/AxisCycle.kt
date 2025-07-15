package net.aquamine.server.util.math

import net.aquamine.api.util.Direction

enum class AxisCycle {

    NONE {

        override fun cycle(axis: Direction.Axis): Direction.Axis = axis

        override fun inverse(): AxisCycle = this
    },
    FORWARD {

        override fun cycle(axis: Direction.Axis): Direction.Axis = cycleDefault(axis)

        override fun inverse(): AxisCycle = BACKWARD
    },
    BACKWARD {

        override fun cycle(axis: Direction.Axis): Direction.Axis = cycleDefault(axis)

        override fun inverse(): AxisCycle = FORWARD
    };

    fun cycle(x: Int, y: Int, z: Int, axis: Direction.Axis): Int = axis.select(x, y, z)

    fun cycle(x: Double, y: Double, z: Double, axis: Direction.Axis): Double = axis.select(x, y, z)

    abstract fun cycle(axis: Direction.Axis): Direction.Axis

    abstract fun inverse(): AxisCycle

    companion object {

        @JvmField
        val AXIS_VALUES: Array<Direction.Axis> = Direction.Axis.entries.toTypedArray()
        private val VALUES = entries.toTypedArray()

        @JvmStatic
        fun between(from: Direction.Axis, to: Direction.Axis): AxisCycle = VALUES[Math.floorMod(to.ordinal - from.ordinal, 3)]

        @JvmStatic
        private fun cycleDefault(axis: Direction.Axis): Direction.Axis = AXIS_VALUES[Math.floorMod(axis.ordinal - 1, 3)]
    }
}
