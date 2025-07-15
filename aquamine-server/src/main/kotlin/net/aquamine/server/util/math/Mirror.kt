package net.aquamine.server.util.math

import net.aquamine.api.util.Direction
import net.aquamine.server.util.serialization.EnumCodecs
import org.kryptonmc.serialization.Codec

enum class Mirror(val rotation: OctahedralGroup) {

    NONE(OctahedralGroup.IDENTITY),
    LEFT_RIGHT(OctahedralGroup.INVERT_Z),
    FRONT_BACK(OctahedralGroup.INVERT_X);

    fun mirror(rotation: Int, rotationCount: Int): Int {
        val midpoint = rotationCount / 2
        val relativeRotation = if (rotation > midpoint) rotation - rotationCount else rotation
        return when (this) {
            FRONT_BACK -> (rotationCount - relativeRotation) % rotationCount
            LEFT_RIGHT -> (midpoint - relativeRotation + rotationCount) % rotationCount
            else -> rotation
        }
    }

    fun rotation(direction: Direction): Rotation {
        val axis = direction.axis
        if ((this != LEFT_RIGHT || axis != Direction.Axis.Z) && (this != FRONT_BACK || axis != Direction.Axis.X)) return Rotation.NONE
        return Rotation.CLOCKWISE_180
    }

    fun mirror(direction: Direction): Direction = when {
        this == FRONT_BACK && direction.axis == Direction.Axis.X -> direction.opposite
        this == LEFT_RIGHT && direction.axis == Direction.Axis.Z -> direction.opposite
        else -> direction
    }

    companion object {

        @JvmField
        val CODEC: Codec<Mirror> = EnumCodecs.of { entries.toTypedArray() }
    }
}
