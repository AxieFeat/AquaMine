package net.aquamine.server.shapes

import it.unimi.dsi.fastutil.doubles.DoubleList
import net.aquamine.api.util.Direction
import net.aquamine.server.shapes.discrete.DiscreteVoxelShape
import net.aquamine.server.shapes.discrete.SliceDiscreteVoxelShape
import net.aquamine.server.shapes.util.CubePointRange

class SliceShape(
    private val delegate: VoxelShape,
    private val axis: Direction.Axis,
    index: Int
) : VoxelShape(makeSlice(delegate.shape, axis, index)) {

    override fun getCoordinates(axis: Direction.Axis): DoubleList = if (axis == this.axis) SLICE_COORDINATES else delegate.getCoordinates(axis)

    companion object {

        private val SLICE_COORDINATES = CubePointRange(1)

        @JvmStatic
        private fun makeSlice(parent: DiscreteVoxelShape, axis: Direction.Axis, index: Int): DiscreteVoxelShape {
            return SliceDiscreteVoxelShape(parent, axis.select(index, 0, 0), axis.select(0, index, 0), axis.select(0, 0, index),
                axis.select(index + 1, parent.sizeX, parent.sizeX), axis.select(parent.sizeY, index + 1, parent.sizeY),
                axis.select(parent.sizeZ, parent.sizeZ, index + 1))
        }
    }
}
