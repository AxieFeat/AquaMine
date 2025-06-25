package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutUnloadChunk(val x: Int, val z: Int) : Packet {

    constructor(reader: BinaryReader) : this(reader.readInt(), reader.readInt())

    override fun write(writer: BinaryWriter) {
        writer.writeInt(x)
        writer.writeInt(z)
    }
}
