package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket
import net.aquamine.server.packet.MovementPacket

@JvmRecord
data class PacketInSetPlayerOnGround(override val onGround: Boolean) : MovementPacket, InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readBoolean())

    override fun write(writer: BinaryWriter) {
        writer.writeBoolean(onGround)
    }

    override fun handle(handler: PlayPacketHandler) {
        // TODO
        //handler.handlePlayerOnGround(this)
    }
}
