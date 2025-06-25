package net.aquamine.server.util.hit

import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i

class BlockHitResult private constructor(
    location: Vec3d,
    val direction: Direction,
    val position: Vec3i,
    private val miss: Boolean,
    val isInside: Boolean
) : HitResult(location) {

    override val type: Type
        get() = if (miss) Type.MISS else Type.BLOCK

    constructor(location: Vec3d, direction: Direction, position: Vec3i,
                inside: Boolean) : this(location, direction, position, false, inside)

    fun withDirection(direction: Direction): BlockHitResult = BlockHitResult(location, direction, position, miss, isInside)

    fun withPosition(position: Vec3i): BlockHitResult = BlockHitResult(location, direction, position, miss, isInside)

    companion object {

        @JvmStatic
        fun miss(location: Vec3d, direction: Direction, position: Vec3i): BlockHitResult {
            return BlockHitResult(location, direction, position, true, false)
        }
    }
}
