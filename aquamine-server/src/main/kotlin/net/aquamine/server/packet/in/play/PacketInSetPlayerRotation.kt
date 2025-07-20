package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket
import net.aquamine.server.packet.MovementPacket

@JvmRecord
data class PacketInSetPlayerRotation(
    val yaw: Float,
    val pitch: Float,
    override val onGround: Boolean
) : MovementPacket, InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        yaw = reader.readFloat(),
        pitch = reader.readFloat(),
        onGround = reader.readBoolean()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeFloat(yaw)
        writer.writeFloat(pitch)
        writer.writeBoolean(onGround)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handlePlayerRotation(this)
    }
}
