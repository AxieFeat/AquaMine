package net.aquamine.server.shapes.discrete

import net.aquamine.api.util.Direction
import net.aquamine.server.util.math.Maths

class SliceDiscreteVoxelShape(
    private val parent: DiscreteVoxelShape,
    private val startX: Int,
    private val startY: Int,
    private val startZ: Int,
    private val endX: Int,
    private val endY: Int,
    private val endZ: Int
) : DiscreteVoxelShape(endX - startX, endY - startY, endZ - startZ) {

    override fun isFull(x: Int, y: Int, z: Int): Boolean = parent.isFull(startX + x, startY + y, startZ + z)

    override fun fill(x: Int, y: Int, z: Int) {
        parent.fill(startX + x, startY + y, startZ + z)
    }

    override fun firstFull(axis: Direction.Axis): Int = clampToShape(axis, parent.firstFull(axis))

    override fun lastFull(axis: Direction.Axis): Int = clampToShape(axis, parent.lastFull(axis))

    private fun clampToShape(axis: Direction.Axis, value: Int): Int {
        val low = axis.select(startX, startY, startZ)
        val high = axis.select(endX, endY, endZ)
        return Maths.clamp(value, low, high) - low
    }

    override fun toString(): String = "SubShape(parent=$parent, sizeX=$sizeX, sizeY=$sizeY, sizeZ=$sizeZ, startX=$startX, startY=$startY," +
            "startZ=$startZ, endX=$endX, endY=$endY, endZ=$endZ)"
}
