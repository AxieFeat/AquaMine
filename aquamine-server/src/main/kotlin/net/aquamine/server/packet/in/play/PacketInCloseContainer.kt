package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInCloseContainer(
    val containerId: Byte
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        containerId = reader.readByte()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeByte(containerId)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleCloseContainer(this)
    }
}