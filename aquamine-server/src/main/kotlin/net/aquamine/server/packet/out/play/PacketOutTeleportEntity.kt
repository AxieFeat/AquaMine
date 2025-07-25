package net.aquamine.server.packet.out.play

import net.aquamine.api.util.Position
import net.aquamine.server.coordinate.Positioning
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.packet.MovementPacket

@JvmRecord
data class PacketOutTeleportEntity(
    override val entityId: Int,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Byte,
    val pitch: Byte,
    override val onGround: Boolean
) : EntityPacket, MovementPacket {

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        x = reader.readDouble(),
        y = reader.readDouble(),
        z = reader.readDouble(),
        yaw = reader.readByte(),
        pitch = reader.readByte(),
        onGround = reader.readBoolean()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeDouble(x)
        writer.writeDouble(y)
        writer.writeDouble(z)
        writer.writeByte(yaw)
        writer.writeByte(pitch)
        writer.writeBoolean(onGround)
    }

    companion object {

        @JvmStatic
        fun create(entity: AquaEntity): PacketOutTeleportEntity = from(entity.id, entity.position, entity.isOnGround)

        @JvmStatic
        fun from(entityId: Int, position: Position, onGround: Boolean): PacketOutTeleportEntity {
            val yaw = Positioning.encodeRotation(position.yaw)
            val pitch = Positioning.encodeRotation(position.pitch)
            return PacketOutTeleportEntity(entityId, position.x, position.y, position.z, yaw, pitch, onGround)
        }
    }
}
