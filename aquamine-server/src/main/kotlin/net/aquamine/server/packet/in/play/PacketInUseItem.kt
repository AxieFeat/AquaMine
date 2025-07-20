package net.aquamine.server.packet.`in`.play

import net.aquamine.api.entity.Hand
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInUseItem(
    val hand: Hand,
    val sequence: Int
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        hand = reader.readEnum(),
        sequence = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(hand)
        writer.writeVarInt(sequence)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleUseItem(this)
    }
}
