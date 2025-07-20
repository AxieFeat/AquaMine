package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutAcknowledgeBlockChange(
    val sequence: Int
) : Packet {

    constructor(reader: BinaryReader) : this(
        sequence = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(sequence)
    }
}
