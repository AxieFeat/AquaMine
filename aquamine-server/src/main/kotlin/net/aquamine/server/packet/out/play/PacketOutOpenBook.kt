package net.aquamine.server.packet.out.play

import net.aquamine.api.entity.Hand
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutOpenBook(val hand: Hand) : Packet {

    constructor(reader: BinaryReader) : this(reader.readEnum<Hand>())

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(hand)
    }
}
