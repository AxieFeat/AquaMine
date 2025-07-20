package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInChatAck(
    val offset: Int
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        offset = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(offset)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleChatAck(this)
    }

}