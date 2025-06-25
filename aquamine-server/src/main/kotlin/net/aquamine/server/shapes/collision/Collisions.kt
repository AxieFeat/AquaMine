package net.aquamine.server.shapes.collision

import net.aquamine.api.util.BoundingBox
import net.aquamine.server.shapes.Shapes
import kotlin.math.max
import kotlin.math.min

/**
 * This is a utility object for handling collisions between shapes and bounding
 * boxes. This contains optimized code from Paper.
 */
object Collisions {

    const val EPSILON: Double = Shapes.EPSILON

    @JvmStatic
    fun isEmpty(box: BoundingBox): Boolean {
        return box.maxX - box.minX < EPSILON &&
                box.maxY - box.minY < EPSILON &&
                box.maxZ - box.minZ < EPSILON
    }

    @JvmStatic
    fun voxelShapeIntersect(box1: BoundingBox, box2: BoundingBox): Boolean {
        return box1.minX - box2.maxX < -EPSILON &&
                box1.maxX - box2.minX > EPSILON &&
                box1.minY - box2.maxY < -EPSILON &&
                box1.maxY - box2.minY > EPSILON &&
                box1.minZ - box2.maxZ < -EPSILON &&
                box1.maxZ - box2.minZ > EPSILON
    }

    @JvmStatic
    fun voxelShapeIntersect(box: BoundingBox, minX: Double, minY: Double, minZ: Double, maxX: Double, maxY: Double, maxZ: Double): Boolean {
        return box.minX - maxX < -EPSILON && box.maxX - minX > EPSILON &&
                box.minY - maxY < -EPSILON && box.maxY - minY > EPSILON &&
                box.minZ - maxZ < -EPSILON && box.maxZ - minZ > EPSILON
    }

    @JvmStatic
    fun collideX(target: BoundingBox, source: BoundingBox, sourceMove: Double): Double {
        return collide(sourceMove, { target.minX - source.maxX }, { target.maxX - source.minX }) {
            source.minY - target.maxY < -EPSILON && source.maxY - target.minY > EPSILON &&
                    source.minZ - target.maxZ < -EPSILON && source.maxZ - target.minZ > EPSILON
        }
    }

    @JvmStatic
    fun collideY(target: BoundingBox, source: BoundingBox, sourceMove: Double): Double {
        return collide(sourceMove, { target.minY - source.maxY }, { target.maxY - source.minY }) {
            source.minX - target.maxX < -EPSILON && source.maxX - target.minX > EPSILON &&
                    source.minZ - target.maxZ < -EPSILON && source.maxZ - target.minZ > EPSILON
        }
    }

    @JvmStatic
    fun collideZ(target: BoundingBox, source: BoundingBox, sourceMove: Double): Double {
        return collide(sourceMove, { target.minZ - source.maxZ }, { target.maxZ - source.minZ }) {
            source.minX - target.maxX < -EPSILON && source.maxX - target.minX > EPSILON &&
                    source.minY - target.maxY < -EPSILON && source.maxY - target.minY > EPSILON
        }
    }

    @JvmStatic
    private inline fun collide(sourceMove: Double, positiveMaxMove: () -> Double, negativeMaxMove: () -> Double, runChecks: () -> Boolean): Double {
        if (sourceMove == 0.0) return 0.0
        if (runChecks()) {
            if (sourceMove >= 0.0) {
                val maxMove = positiveMaxMove()
                if (maxMove < -EPSILON) return sourceMove
                return min(maxMove, sourceMove)
            } else {
                val maxMove = negativeMaxMove()
                if (maxMove > EPSILON) return sourceMove
                return max(maxMove, sourceMove)
            }
        }
        return sourceMove
    }
}
