package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInVehicleMove(
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        x = reader.readDouble(),
        y = reader.readDouble(),
        z = reader.readDouble(),
        yaw = reader.readFloat(),
        pitch = reader.readFloat()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeDouble(x)
        writer.writeDouble(y)
        writer.writeDouble(z)
        writer.writeFloat(yaw)
        writer.writeFloat(pitch)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleVehicleMove(this)
    }
}
