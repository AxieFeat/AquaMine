package net.aquamine.server.packet.out.login

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
@Suppress("ArrayInDataClass")
data class PacketOutPluginRequest(
    val id: Int,
    val channel: String,
    val data: ByteArray
) : Packet {

    constructor(reader: BinaryReader) : this(
        id = reader.readVarInt(),
        channel = reader.readString(),
        data = reader.readAllBytes()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(id)
        writer.writeString(channel)
        writer.writeBytes(data)
    }
}
