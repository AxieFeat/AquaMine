package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInSetHeldItem(
    val slot: Short
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        slot = reader.readShort()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeShort(slot)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleSetHeldItem(this)
    }
}
