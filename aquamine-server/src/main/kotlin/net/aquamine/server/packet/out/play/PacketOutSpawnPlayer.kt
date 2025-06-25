package net.aquamine.server.packet.out.play

import net.aquamine.api.util.Position
import net.aquamine.server.coordinate.Positioning
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket
import java.util.UUID

/**
 * Spawn a player for the client.
 */
@JvmRecord
data class PacketOutSpawnPlayer(
    override val entityId: Int,
    val uuid: UUID,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Byte,
    val pitch: Byte
) : EntityPacket {

    constructor(reader: BinaryReader) : this(reader.readVarInt(), reader.readUUID(), reader.readDouble(), reader.readDouble(), reader.readDouble(),
        reader.readByte(), reader.readByte())

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeUUID(uuid)
        writer.writeDouble(x)
        writer.writeDouble(y)
        writer.writeDouble(z)
        writer.writeByte(yaw)
        writer.writeByte(pitch)
    }

    companion object {

        @JvmStatic
        fun create(player: KryptonPlayer): PacketOutSpawnPlayer = from(player.id, player.uuid, player.position)

        @JvmStatic
        fun from(entityId: Int, uuid: UUID, pos: Position): PacketOutSpawnPlayer {
            val pitch = Positioning.encodeRotation(pos.pitch)
            val yaw = Positioning.encodeRotation(pos.yaw)
            return PacketOutSpawnPlayer(entityId, uuid, pos.x, pos.y, pos.z, yaw, pitch)
        }
    }
}
