package net.aquamine.server.packet.`in`.play

import net.aquamine.api.entity.Hand
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInSwingArm(val hand: Hand) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readEnum<Hand>())

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(hand)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleSwingArm(this)
    }
}
