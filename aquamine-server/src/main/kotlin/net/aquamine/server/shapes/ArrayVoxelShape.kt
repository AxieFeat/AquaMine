package net.aquamine.server.shapes

import it.unimi.dsi.fastutil.doubles.AbstractDoubleList
import it.unimi.dsi.fastutil.doubles.DoubleArrayList
import it.unimi.dsi.fastutil.doubles.DoubleList
import net.aquamine.api.util.BoundingBox
import net.aquamine.api.util.Direction
import net.aquamine.server.shapes.collision.Collisions
import net.aquamine.server.shapes.discrete.DiscreteVoxelShape
import net.aquamine.server.shapes.util.BooleanOperator

class ArrayVoxelShape(
    shape: DiscreteVoxelShape,
    private val xs: DoubleList,
    private val ys: DoubleList,
    private val zs: DoubleList,
    boundingBoxesRepresentation: Array<BoundingBox>?,
    private val offsetX: Double,
    private val offsetY: Double,
    private val offsetZ: Double
) : VoxelShape(shape) {

    private val boundingBoxesRepresentation = boundingBoxesRepresentation ?: toBoundingBoxes().toTypedArray()

    init {
        val sizeX = shape.sizeX() + 1
        val sizeY = shape.sizeY() + 1
        val sizeZ = shape.sizeZ() + 1
        require(sizeX == xs.size && sizeY == ys.size && sizeZ == zs.size) {
            "Lengths of point arrays must be consistent with the size of the VoxelShape!"
        }
    }

    constructor(shape: DiscreteVoxelShape, xs: DoubleList, ys: DoubleList, zs: DoubleList) : this(shape, xs, ys, zs, null, 0.0, 0.0, 0.0)

    override fun getCoordinates(axis: Direction.Axis): DoubleList = when (axis) {
        Direction.Axis.X -> xs
        Direction.Axis.Y -> ys
        Direction.Axis.Z -> zs
    }

    override fun intersects(box: BoundingBox): Boolean {
        forBoundingBoxes { minX, minY, minZ, maxX, maxY, maxZ ->
            if (Collisions.voxelShapeIntersect(box, minX, minY, minZ, maxX, maxY, maxZ)) return true
        }
        return false
    }

    override fun move(x: Double, y: Double, z: Double): VoxelShape {
        if (x == 0.0 && y == 0.0 && z == 0.0) return this
        val offsetX = if (xs is DoubleListOffsetExposed) offsetX + x else x
        val offsetY = if (ys is DoubleListOffsetExposed) offsetY + y else y
        val offsetZ = if (zs is DoubleListOffsetExposed) offsetZ + z else z
        val offsetXs = convertOffsetList(xs, offsetX)
        val offsetYs = convertOffsetList(ys, offsetY)
        val offsetZs = convertOffsetList(zs, offsetZ)
        return ArrayVoxelShape(shape, offsetXs, offsetYs, offsetZs, boundingBoxesRepresentation, offsetX, offsetY, offsetZ)
    }

    override fun optimize(): VoxelShape {
        if (this === Shapes.empty() || boundingBoxesRepresentation.isEmpty()) return this
        var simplified = Shapes.empty()
        forBoundingBoxes { minX, minY, minZ, maxX, maxY, maxZ ->
            simplified = Shapes.joinUnoptimized(simplified, Shapes.box(minX, minY, minZ, maxX, maxY, maxZ), BooleanOperator.OR)
        }
        if (simplified !is ArrayVoxelShape) return simplified
        val casted = simplified
        if (casted.boundingBoxesRepresentation.size == 1) return BoundingBoxVoxelShape(casted.boundingBoxesRepresentation[0]).optimize()
        return simplified
    }

    override fun forAllBoxes(consumer: Shapes.DoubleLineConsumer) {
        @Suppress("SENSELESS_COMPARISON") // Due to class initialization, this may actually be null.
        if (boundingBoxesRepresentation == null) return super.forAllBoxes(consumer)
        forBoundingBoxes { minX, minY, minZ, maxX, maxY, maxZ -> consumer.consume(minX, minY, minZ, maxX, maxY, maxZ) }
    }

    override fun toBoundingBoxes(): List<BoundingBox> {
        @Suppress("SENSELESS_COMPARISON") // Due to class initialization, this may actually be null.
        if (boundingBoxesRepresentation == null) return super.toBoundingBoxes()
        return boundingBoxesRepresentation.map { it.move(offsetX, offsetY, offsetZ) }
    }

    override fun toString(): String = "ArrayVoxelShape(shape=$shape, xs=$xs, ys=$ys, zs=$zs)"

    private inline fun forBoundingBoxes(action: (Double, Double, Double, Double, Double, Double) -> Unit) {
        boundingBoxesRepresentation.forEach {
            action(
                it.minX + offsetX, it.minY + offsetY, it.minZ + offsetZ,
                it.maxX + offsetX, it.maxY + offsetY, it.maxZ + offsetZ
            )
        }
    }

    class DoubleListOffsetExposed(val list: DoubleArrayList, val offset: Double) : AbstractDoubleList() {

        override val size: Int
            get() = list.size

        override fun getDouble(index: Int): Double = list.getDouble(index) + offset
    }

    companion object {

        @JvmStatic
        private fun convertList(list: DoubleList): DoubleArrayList {
            if (list is DoubleArrayList) return list
            return DoubleArrayList.wrap(list.toDoubleArray())
        }

        @JvmStatic
        private fun convertOffsetList(list: DoubleList, offset: Double): DoubleListOffsetExposed {
            if (list is DoubleListOffsetExposed) return DoubleListOffsetExposed(list.list, offset)
            return DoubleListOffsetExposed(convertList(list), offset)
        }
    }
}
