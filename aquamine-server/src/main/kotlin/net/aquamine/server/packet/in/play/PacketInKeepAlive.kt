package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInKeepAlive(
    val id: Long
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        id = reader.readLong()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeLong(id)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleKeepAlive(this)
    }
}
