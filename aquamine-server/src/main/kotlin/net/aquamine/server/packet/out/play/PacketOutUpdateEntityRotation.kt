package net.aquamine.server.packet.out.play

import net.aquamine.server.coordinate.Positioning
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.packet.MovementPacket

@JvmRecord
data class PacketOutUpdateEntityRotation(
    override val entityId: Int,
    val yaw: Byte,
    val pitch: Byte,
    override val onGround: Boolean
) : EntityPacket, MovementPacket {

    constructor(
        entityId: Int,
        yaw: Float,
        pitch: Float,
        onGround: Boolean
    ) : this(
        entityId = entityId,
        yaw = Positioning.encodeRotation(yaw),
        pitch = Positioning.encodeRotation(pitch),
        onGround = onGround
    )

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        yaw = reader.readByte(),
        pitch = reader.readByte(),
        onGround = reader.readBoolean()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeByte(yaw)
        writer.writeByte(pitch)
        writer.writeBoolean(onGround)
    }
}
