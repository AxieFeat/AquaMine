package net.aquamine.server.entity.hanging

import net.aquamine.api.entity.hanging.HangingEntity
import net.aquamine.api.util.BoundingBox
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.hanging.HangingEntitySerializer
import net.aquamine.server.util.enumhelper.Directions
import net.aquamine.server.world.AquaWorld

abstract class AquaHangingEntity(world: AquaWorld) : AquaEntity(world), HangingEntity {

    override val serializer: EntitySerializer<out AquaHangingEntity>
        get() = HangingEntitySerializer

    internal var centerPosition: Vec3i? = null
    final override var direction: Direction = Direction.SOUTH
        set(value) {
            require(value.axis.isHorizontal())
            field = value
            // Each horizontal direction is a half turn (90 degrees) away from each other.
            // The 2D data value is 0 = south, 1 = west, 2 = north, 3 = east.
            teleport(position.withPitch(Directions.data2D(value) * HALF_TURN_DEGREES))
            recalculateBoundingBox()
        }

    abstract fun width(): Int

    abstract fun height(): Int

    private fun recalculateBoundingBox() {
        var x = centerPosition!!.x + BLOCK_CENTER_OFFSET
        var y = centerPosition!!.y + BLOCK_CENTER_OFFSET
        var z = centerPosition!!.z + BLOCK_CENTER_OFFSET

        // for EAST, the x becomes centerX + 1/32, and for WEST, the x becomes centerX + 31/32
        x -= direction.normalX * ONE_BLOCK_PIXELS
        // for SOUTH, the z becomes centerZ + 1/32, and for NORTH, the z becomes centerZ + 31/32
        z -= direction.normalZ * ONE_BLOCK_PIXELS
        if (height() % TWO_BLOCKS_PICTURE_PIXELS == 0) y += BLOCK_CENTER_OFFSET

        val antiClockwise = Directions.antiClockwise(direction)
        if (width() % TWO_BLOCKS_PICTURE_PIXELS == 0) {
            // for direction NORTH (anti clockwise WEST), the x becomes centerX
            // for direction SOUTH (anti clockwise EAST), the x becomes centerX + 1
            x += BLOCK_CENTER_OFFSET * antiClockwise.normalX
            // for direction EAST (anti clockwise NORTH), the z becomes centerZ
            // for direction WEST (anti clockwise SOUTH), the z becomes centerZ + 1
            z += BLOCK_CENTER_OFFSET * antiClockwise.normalZ
        }
        teleport(position.withCoordinates(x, y, z))

        var widthX = width().toDouble()
        var height = height().toDouble()
        var widthZ = width().toDouble()
        if (direction.axis == Direction.Axis.Z) widthZ = 1.0 else widthX = 1.0
        widthX /= TWO_BLOCKS_PICTURE_PIXELS
        height /= TWO_BLOCKS_PICTURE_PIXELS
        widthZ /= TWO_BLOCKS_PICTURE_PIXELS
        boundingBox = BoundingBox(x - widthX, y - height, z - widthZ, x + widthX, y + height, z + widthZ)
    }

    companion object {

        // The offset to add to a block coordinate to make it in the centre of the block.
        private const val BLOCK_CENTER_OFFSET = 0.5
        private const val TWO_BLOCKS_PICTURE_PIXELS = 16 * 2 // 16 pixels on a picture = 1 block.
        private const val ONE_BLOCK_PIXELS = 15.0 / 32.0
        private const val HALF_TURN_DEGREES = 90F
    }
}
