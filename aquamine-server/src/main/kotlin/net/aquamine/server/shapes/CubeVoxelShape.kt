package net.aquamine.server.shapes

import it.unimi.dsi.fastutil.doubles.DoubleList
import net.aquamine.api.util.Direction
import net.aquamine.server.shapes.discrete.DiscreteVoxelShape
import net.aquamine.server.shapes.util.CubePointRange
import net.aquamine.server.util.math.Maths

class CubeVoxelShape(shape: DiscreteVoxelShape) : VoxelShape(shape) {

    override fun getCoordinates(axis: Direction.Axis): DoubleList = CubePointRange(shape.size(axis))

    override fun findIndex(axis: Direction.Axis, position: Double): Int {
        val size = shape.size(axis).toDouble()
        return Maths.floor(Maths.clamp(position * size, -1.0, size))
    }

    override fun toString(): String = "CubeVoxelShape(shape=$shape)"
}
