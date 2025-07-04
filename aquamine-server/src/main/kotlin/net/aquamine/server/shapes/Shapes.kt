package net.aquamine.server.shapes

import com.google.common.math.DoubleMath
import it.unimi.dsi.fastutil.doubles.DoubleArrayList
import it.unimi.dsi.fastutil.doubles.DoubleList
import net.aquamine.api.util.BoundingBox
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Direction.Axis
import net.aquamine.server.shapes.collision.Collisions
import net.aquamine.server.shapes.discrete.BitSetDiscreteVoxelShape
import net.aquamine.server.shapes.merger.DiscreteCubeMerger
import net.aquamine.server.shapes.merger.IdenticalMerger
import net.aquamine.server.shapes.merger.IndexMerger
import net.aquamine.server.shapes.merger.IndirectMerger
import net.aquamine.server.shapes.merger.NonOverlappingMerger
import net.aquamine.server.shapes.util.BooleanOperator
import net.aquamine.server.shapes.util.CubePointRange
import net.aquamine.server.util.math.Maths
import net.aquamine.server.util.math.AxisCycle
import java.util.Objects

object Shapes {

    const val EPSILON: Double = 1.0E-7
    private const val P_INFINITY = Double.POSITIVE_INFINITY
    private const val N_INFINITY = Double.NEGATIVE_INFINITY
    private val UNOPTIMIZED_BLOCK = CubeVoxelShape(BitSetDiscreteVoxelShape(1, 1, 1).apply { fill(0, 0, 0) })
    private val EMPTY = ArrayVoxelShape(BitSetDiscreteVoxelShape(0, 0, 0), emptyList(), emptyList(), emptyList())
    @JvmField
    val OPTIMIZED_BLOCK: BoundingBoxVoxelShape = BoundingBoxVoxelShape(BoundingBox(0.0, 0.0, 0.0, 1.0, 1.0, 1.0))
    @JvmField
    val INFINITY: VoxelShape = box(N_INFINITY, N_INFINITY, N_INFINITY, P_INFINITY, P_INFINITY, P_INFINITY)

    @JvmStatic
    fun empty(): VoxelShape = EMPTY

    @JvmStatic
    fun unoptimizedBlock(): VoxelShape = UNOPTIMIZED_BLOCK

    @JvmStatic
    fun block(): VoxelShape = OPTIMIZED_BLOCK

    @JvmStatic
    fun box(minX: Double, minY: Double, minZ: Double, maxX: Double, maxY: Double, maxZ: Double): VoxelShape {
        require(minX <= maxX && minY <= maxY && minZ <= maxZ) {
            "Invalid bounds! Minimums must be smaller than maximums! Minimums: $minX, $minY, $minZ, Maximums: $maxX, $maxY, $maxZ"
        }
        return create(minX, minY, minZ, maxX, maxY, maxZ)
    }

    @JvmStatic
    fun create(box: BoundingBox): VoxelShape = BoundingBoxVoxelShape(box)

    @JvmStatic
    fun create(minX: Double, minY: Double, minZ: Double, maxX: Double, maxY: Double, maxZ: Double): VoxelShape {
        @Suppress("SimplifyNegatedBinaryExpression") // Any of these could be NaN
        if (!(maxX - minX < EPSILON) && !(maxY - minY < EPSILON) && !(maxZ - minZ < EPSILON)) {
            return BoundingBoxVoxelShape(BoundingBox(minX, minY, minZ, maxX, maxY, maxZ))
        }
        return empty()
    }

    @JvmStatic
    fun or(mainShape: VoxelShape, otherShape: VoxelShape): VoxelShape = join(mainShape, otherShape, BooleanOperator.OR)

    @JvmStatic
    fun or(mainShape: VoxelShape, vararg otherShapes: VoxelShape): VoxelShape = otherShapes.fold(mainShape, Shapes::or)

    @JvmStatic
    fun join(mainShape: VoxelShape, otherShape: VoxelShape, operator: BooleanOperator): VoxelShape =
        joinUnoptimized(mainShape, otherShape, operator).optimize()

    @JvmStatic
    fun joinUnoptimized(mainShape: VoxelShape, otherShape: VoxelShape, operator: BooleanOperator): VoxelShape {
        require(!operator.apply(false, false)) { "Cannot use operator that evaluates to true with two false values!" }
        if (mainShape === otherShape) {
            if (operator.apply(true, true)) return mainShape
            return empty()
        }
        val firstOnly = operator.apply(true, false)
        val secondOnly = operator.apply(false, true)
        if (mainShape.isEmpty()) {
            if (secondOnly) return otherShape
            return empty()
        }
        if (otherShape.isEmpty()) {
            if (firstOnly) return mainShape
            return empty()
        }
        val mergerX = createIndexMerger(1, mainShape.getCoordinates(Axis.X), otherShape.getCoordinates(Axis.X), firstOnly, secondOnly)
        val mergerYLower = mainShape.getCoordinates(Axis.Y)
        val mergerY = createIndexMerger(mergerX.size() - 1, mergerYLower, otherShape.getCoordinates(Axis.Y), firstOnly, secondOnly)
        val mergerZSize = (mergerX.size() - 1) * (mergerY.size() - 1)
        val mergerZ = createIndexMerger(mergerZSize, mainShape.getCoordinates(Axis.Z), otherShape.getCoordinates(Axis.Z), firstOnly, secondOnly)
        val shape = BitSetDiscreteVoxelShape.join(mainShape.shape, otherShape.shape, mergerX, mergerY, mergerZ, operator)
        if (mergerX is DiscreteCubeMerger && mergerY is DiscreteCubeMerger && mergerZ is DiscreteCubeMerger) return CubeVoxelShape(shape)
        return ArrayVoxelShape(shape, mergerX.asList(), mergerY.asList(), mergerZ.asList())
    }

    @JvmStatic
    fun joinIsNotEmpty(mainShape: VoxelShape, otherShape: VoxelShape, operator: BooleanOperator): Boolean {
        if (operator == BooleanOperator.AND) {
            if (mainShape is BoundingBoxVoxelShape && otherShape is BoundingBoxVoxelShape) {
                return Collisions.voxelShapeIntersect(mainShape.box, otherShape.box)
            }
            if (mainShape is BoundingBoxVoxelShape && otherShape is ArrayVoxelShape) return otherShape.intersects(mainShape.box)
            if (mainShape is ArrayVoxelShape && otherShape is BoundingBoxVoxelShape) return mainShape.intersects(otherShape.box)
        }
        return defaultJoinIsNotEmpty(mainShape, otherShape, operator)
    }

    @JvmStatic
    private fun defaultJoinIsNotEmpty(mainShape: VoxelShape, otherShape: VoxelShape, operator: BooleanOperator): Boolean {
        require(!operator.apply(false, false)) { "Cannot use operator that evaluates to true with two false values!" }
        val mainEmpty = mainShape.isEmpty()
        val otherEmpty = otherShape.isEmpty()
        if (mainEmpty || otherEmpty) return operator.apply(!mainEmpty, !otherEmpty)
        if (mainShape === otherShape) return operator.apply(true, true)
        val firstOnly = operator.apply(true, false)
        val secondOnly = operator.apply(false, true)
        AxisCycle.AXIS_VALUES.forEach {
            if (mainShape.max(it) < otherShape.min(it) - EPSILON) return firstOnly || secondOnly
            if (otherShape.max(it) < mainShape.min(it) - EPSILON) return firstOnly || secondOnly
        }
        val mergerX = createIndexMerger(1, mainShape.getCoordinates(Axis.X), otherShape.getCoordinates(Axis.X), firstOnly, secondOnly)
        val mergerYLower = mainShape.getCoordinates(Axis.Y)
        val mergerY = createIndexMerger(mergerX.size() - 1, mergerYLower, otherShape.getCoordinates(Axis.Y), firstOnly, secondOnly)
        val mergerZSize = (mergerX.size() - 1) * (mergerY.size() - 1)
        val mergerZ = createIndexMerger(mergerZSize, mainShape.getCoordinates(Axis.Z), otherShape.getCoordinates(Axis.Z), firstOnly, secondOnly)
        return !mergerX.forMergedIndices { x1, x2, _ ->
            mergerY.forMergedIndices { y1, y2, _ ->
                mergerZ.forMergedIndices { z1, z2, _ ->
                    !operator.apply(mainShape.shape.isFullWide(x1, y1, z1), otherShape.shape.isFullWide(x2, y2, z2))
                }
            }
        }
    }

    @JvmStatic
    fun faceShape(shape: VoxelShape, direction: Direction): VoxelShape {
        if (shape === UNOPTIMIZED_BLOCK || shape === OPTIMIZED_BLOCK) return OPTIMIZED_BLOCK
        if (shape === empty()) return empty()
        if (shape is BoundingBoxVoxelShape) {
            val box = shape.box
            when (direction) {
                Direction.WEST, Direction.EAST -> { // -X, +X
                    val useEmpty = if (direction == Direction.EAST) {
                        !DoubleMath.fuzzyEquals(box.maxX, 1.0, Collisions.EPSILON)
                    } else {
                        !DoubleMath.fuzzyEquals(box.minX, 0.0, Collisions.EPSILON)
                    }
                    if (useEmpty) return empty()
                    return BoundingBoxVoxelShape(BoundingBox(0.0, box.minY, box.minZ, 1.0, box.maxY, box.maxZ)).optimize()
                }
                Direction.DOWN, Direction.UP -> { // -Y, +Y
                    val useEmpty = if (direction == Direction.UP) {
                        !DoubleMath.fuzzyEquals(box.maxY, 1.0, Collisions.EPSILON)
                    } else {
                        !DoubleMath.fuzzyEquals(box.minY, 0.0, Collisions.EPSILON)
                    }
                    if (useEmpty) return empty()
                    return BoundingBoxVoxelShape(BoundingBox(box.minX, 0.0, box.minZ, box.maxX, 1.0, box.maxZ)).optimize()
                }
                Direction.NORTH, Direction.SOUTH -> { // -Z, +Z
                    val useEmpty = if (direction == Direction.SOUTH) {
                        !DoubleMath.fuzzyEquals(box.maxZ, 1.0, Collisions.EPSILON)
                    } else {
                        !DoubleMath.fuzzyEquals(box.minZ, 0.0, Collisions.EPSILON)
                    }
                    if (useEmpty) return empty()
                    return BoundingBoxVoxelShape(BoundingBox(box.minX, box.minY, 0.0, box.maxX, box.maxY, 1.0)).optimize()
                }
            }
        }
        return defaultFaceShape(shape, direction)
    }

    @JvmStatic
    private fun defaultFaceShape(shape: VoxelShape, direction: Direction): VoxelShape {
        if (shape === block()) return block()
        val axis = direction.axis
        val boundIsBlock: Boolean
        val index: Int
        if (direction.axisDirection == Direction.AxisDirection.POSITIVE) {
            boundIsBlock = DoubleMath.fuzzyEquals(shape.max(axis), 1.0, EPSILON)
            index = shape.shape.size(axis) - 1
        } else {
            boundIsBlock = DoubleMath.fuzzyEquals(shape.min(axis), 0.0, EPSILON)
            index = 0
        }
        if (!boundIsBlock) return empty()
        // First optimize converts to ArrayVoxelShape, second may convert to BoundingBoxVoxelShape
        return SliceShape(shape, axis, index).optimize().optimize()
    }

    @JvmStatic
    private fun createIndexMerger(size: Int, lower: DoubleList, upper: DoubleList, firstOnly: Boolean, secondOnly: Boolean): IndexMerger {
        if (lower.getDouble(0) == N_INFINITY && lower.getDouble(lower.size - 1) == P_INFINITY) {
            return IndirectMerger(lower, upper, firstOnly, secondOnly)
        }
        return lessCommonMerge(size, lower, upper, firstOnly, secondOnly)
    }

    @JvmStatic
    private fun lessCommonMerge(size: Int, lower: DoubleList, upper: DoubleList, firstOnly: Boolean, secondOnly: Boolean): IndexMerger {
        val lowerMaxIndex = lower.size - 1
        val upperMaxIndex = upper.size - 1
        if (lower is CubePointRange && upper is CubePointRange) {
            val lcm = Maths.lcm(lowerMaxIndex, upperMaxIndex)
            @Suppress("MagicNumber")
            if (size * lcm <= 256L) return DiscreteCubeMerger(lowerMaxIndex, upperMaxIndex)
        }
        if (lowerMaxIndex == upperMaxIndex && Objects.equals(lower, upper)) return IdenticalMerger(lower)
        if (lower.getDouble(lowerMaxIndex) < upper.getDouble(0) - EPSILON) return NonOverlappingMerger(lower, upper, false)
        if (upper.getDouble(upperMaxIndex) < lower.getDouble(0) - EPSILON) return NonOverlappingMerger(upper, lower, true)
        return IndirectMerger(lower, upper, firstOnly, secondOnly)
    }

    @JvmStatic
    private fun emptyList(): DoubleList = DoubleArrayList(doubleArrayOf(0.0))

    fun interface DoubleLineConsumer {

        fun consume(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double)
    }
}
