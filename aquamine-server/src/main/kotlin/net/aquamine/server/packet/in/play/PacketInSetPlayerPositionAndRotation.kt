package net.aquamine.server.packet.`in`.play

import net.aquamine.api.util.Position
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket
import net.aquamine.server.packet.MovementPacket

@JvmRecord
data class PacketInSetPlayerPositionAndRotation(
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
    override val onGround: Boolean
) : MovementPacket, InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readDouble(), reader.readDouble(), reader.readDouble(), reader.readFloat(), reader.readFloat(),
        reader.readBoolean())

    fun position(): Position = Position(x, y, z, yaw, pitch)

    override fun write(writer: BinaryWriter) {
        writer.writeDouble(x)
        writer.writeDouble(y)
        writer.writeDouble(z)
        writer.writeFloat(yaw)
        writer.writeFloat(pitch)
        writer.writeBoolean(onGround)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handlePlayerPositionAndRotation(this)
    }
}
