package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInPickItem(
    val slot: Int
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        slot = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(slot)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handlePickItem(this)
    }
}