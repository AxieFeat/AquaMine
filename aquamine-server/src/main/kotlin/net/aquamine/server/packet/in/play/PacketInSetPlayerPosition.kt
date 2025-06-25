package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket
import net.aquamine.server.packet.MovementPacket

@JvmRecord
data class PacketInSetPlayerPosition(
    val x: Double,
    val y: Double,
    val z: Double,
    override val onGround: Boolean
) : MovementPacket, InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readDouble(), reader.readDouble(), reader.readDouble(), reader.readBoolean())

    override fun write(writer: BinaryWriter) {
        writer.writeDouble(x)
        writer.writeDouble(y)
        writer.writeDouble(z)
        writer.writeBoolean(onGround)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handlePlayerPosition(this)
    }
}
