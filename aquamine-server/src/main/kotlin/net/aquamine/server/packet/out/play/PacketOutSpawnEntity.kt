package net.aquamine.server.packet.out.play

import net.aquamine.api.util.Position
import net.aquamine.api.util.Vec3d
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.coordinate.Positioning
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import java.util.UUID

@JvmRecord
data class PacketOutSpawnEntity(
    override val entityId: Int,
    val uuid: UUID,
    val type: AquaEntityType<*>,
    val x: Double,
    val y: Double,
    val z: Double,
    val pitch: Byte,
    val yaw: Byte,
    val headYaw: Byte,
    val data: Int,
    val velocityX: Short,
    val velocityY: Short,
    val velocityZ: Short
) : EntityPacket {

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        uuid = reader.readUUID(),
        type = reader.readById(AquaRegistries.ENTITY_TYPE)!!,
        x = reader.readDouble(),
        y = reader.readDouble(),
        z = reader.readDouble(),
        pitch = reader.readByte(),
        yaw = reader.readByte(),
        headYaw = reader.readByte(),
        data = reader.readVarInt(),
        velocityX = reader.readShort(),
        velocityY = reader.readShort(),
        velocityZ = reader.readShort()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeUUID(uuid)
        writer.writeId(AquaRegistries.ENTITY_TYPE, type)
        writer.writeDouble(x)
        writer.writeDouble(y)
        writer.writeDouble(z)
        writer.writeByte(pitch)
        writer.writeByte(yaw)
        writer.writeByte(headYaw)
        writer.writeVarInt(data)
        writer.writeShort(velocityX)
        writer.writeShort(velocityY)
        writer.writeShort(velocityZ)
    }

    companion object {

        @JvmStatic
        fun create(entity: AquaEntity): PacketOutSpawnEntity {
            return from(entity.id, entity.uuid, entity.type, entity.position, entity.headYaw(), 0, entity.velocity)
        }

        @JvmStatic
        fun from(entityId: Int, uuid: UUID, type: AquaEntityType<*>, pos: Position, headYaw: Float, data: Int,
                 velocity: Vec3d): PacketOutSpawnEntity {
            val pitch = Positioning.encodeRotation(pos.pitch)
            val yaw = Positioning.encodeRotation(pos.yaw)
            val encodedHeadYaw = Positioning.encodeRotation(headYaw)
            val dx = Positioning.encodeVelocity(velocity.x)
            val dy = Positioning.encodeVelocity(velocity.y)
            val dz = Positioning.encodeVelocity(velocity.z)
            return PacketOutSpawnEntity(entityId, uuid, type, pos.x, pos.y, pos.z, pitch, yaw, encodedHeadYaw, data, dx, dy, dz)
        }
    }
}
