package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInSelectTrade(
    val selectedSlot: Int
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        selectedSlot = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(selectedSlot)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleSelectTrade(this)
    }
}