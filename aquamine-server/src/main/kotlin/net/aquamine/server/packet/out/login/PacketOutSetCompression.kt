package net.aquamine.server.packet.out.login

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

/**
 * Instructs the client to set a threshold for packet compression. Any packets
 * that are >= [threshold] in size will be compressed.
 */
@JvmRecord
data class PacketOutSetCompression(val threshold: Int) : Packet {

    constructor(reader: BinaryReader) : this(reader.readVarInt())

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(threshold)
    }
}
