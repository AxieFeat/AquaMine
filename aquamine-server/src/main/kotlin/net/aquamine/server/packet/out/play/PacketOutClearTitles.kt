package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutClearTitles(val reset: Boolean) : Packet {

    constructor(reader: BinaryReader) : this(reader.readBoolean())

    override fun write(writer: BinaryWriter) {
        writer.writeBoolean(reset)
    }
}
