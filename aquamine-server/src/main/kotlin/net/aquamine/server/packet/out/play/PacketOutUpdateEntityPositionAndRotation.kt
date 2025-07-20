package net.aquamine.server.packet.out.play

import net.aquamine.api.util.Position
import net.aquamine.server.coordinate.Positioning
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.packet.MovementPacket

@JvmRecord
data class PacketOutUpdateEntityPositionAndRotation(
    override val entityId: Int,
    val deltaX: Short,
    val deltaY: Short,
    val deltaZ: Short,
    val yaw: Byte,
    val pitch: Byte,
    override val onGround: Boolean
) : EntityPacket, MovementPacket {

    constructor(
        entityId: Int,
        dx: Short,
        dy: Short,
        dz: Short,
        yaw: Float,
        pitch: Float,
        onGround: Boolean
    ) : this(
        entityId = entityId,
        deltaX = dx,
        deltaY = dy,
        deltaZ = dz,
        yaw = Positioning.encodeRotation(yaw),
        pitch = Positioning.encodeRotation(pitch),
        onGround = onGround
    )

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        deltaX = reader.readShort(),
        deltaY = reader.readShort(),
        deltaZ = reader.readShort(),
        yaw = reader.readByte(),
        pitch = reader.readByte(),
        onGround = reader.readBoolean()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeShort(deltaX)
        writer.writeShort(deltaY)
        writer.writeShort(deltaZ)
        writer.writeByte(yaw)
        writer.writeByte(pitch)
        writer.writeBoolean(onGround)
    }

    companion object {

        @JvmStatic
        fun create(entityId: Int, oldPos: Position, newPos: Position, onGround: Boolean): PacketOutUpdateEntityPositionAndRotation {
            val deltaX = Positioning.calculateDelta(newPos.x, oldPos.x)
            val deltaY = Positioning.calculateDelta(newPos.y, oldPos.y)
            val deltaZ = Positioning.calculateDelta(newPos.z, oldPos.z)
            return PacketOutUpdateEntityPositionAndRotation(entityId, deltaX, deltaY, deltaZ, newPos.yaw, newPos.pitch, onGround)
        }
    }
}
