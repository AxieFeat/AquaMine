package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutSetCenterChunk(
    val x: Int,
    val z: Int
) : Packet {

    constructor(reader: BinaryReader) : this(
        x = reader.readVarInt(),
        z = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(x)
        writer.writeVarInt(z)
    }
}
