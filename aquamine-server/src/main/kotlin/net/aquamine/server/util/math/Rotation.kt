package net.aquamine.server.util.math

import net.aquamine.api.util.Direction
import net.aquamine.server.util.enumhelper.Directions
import net.aquamine.server.util.serialization.EnumCodecs
import net.aquamine.serialization.Codec

enum class Rotation(val id: String, val rotation: OctahedralGroup) {

    NONE("none", OctahedralGroup.IDENTITY),
    CLOCKWISE_90("clockwise_90", OctahedralGroup.ROT_90_Y_NEG),
    CLOCKWISE_180("180", OctahedralGroup.ROT_180_FACE_XZ),
    ANTICLOCKWISE_90("counterclockwise_90", OctahedralGroup.ROT_90_Y_POS);

    fun getRotated(rotation: Rotation): Rotation = when (rotation) {
        CLOCKWISE_180 -> when (this) {
            NONE -> CLOCKWISE_180
            CLOCKWISE_90 -> ANTICLOCKWISE_90
            CLOCKWISE_180 -> NONE
            ANTICLOCKWISE_90 -> CLOCKWISE_90
        }
        ANTICLOCKWISE_90 -> when (this) {
            NONE -> ANTICLOCKWISE_90
            CLOCKWISE_90 -> NONE
            CLOCKWISE_180 -> CLOCKWISE_90
            ANTICLOCKWISE_90 -> CLOCKWISE_180
        }
        CLOCKWISE_90 -> when (this) {
            NONE -> CLOCKWISE_90
            CLOCKWISE_90 -> CLOCKWISE_180
            CLOCKWISE_180 -> ANTICLOCKWISE_90
            ANTICLOCKWISE_90 -> NONE
        }
        else -> this
    }

    fun rotate(facing: Direction): Direction {
        if (facing.axis == Direction.Axis.Y) return facing
        return when (this) {
            CLOCKWISE_90 -> Directions.clockwise(facing)
            CLOCKWISE_180 -> facing.opposite
            ANTICLOCKWISE_90 -> Directions.antiClockwise(facing)
            else -> facing
        }
    }

    fun rotate(rotation: Int, positionCount: Int): Int = when (this) {
        CLOCKWISE_90 -> (rotation + positionCount / 4) % positionCount
        CLOCKWISE_180 -> (rotation + positionCount / 2) % positionCount
        ANTICLOCKWISE_90 -> (rotation + positionCount * 3 / 4) % positionCount
        else -> rotation
    }

    companion object {

        @JvmField
        val CODEC: Codec<Rotation> = EnumCodecs.of({ entries.toTypedArray() }, { it.id })
    }
}
