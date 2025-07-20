package net.aquamine.server.packet.out.play

import net.kyori.adventure.key.Key
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
@Suppress("ArrayInDataClass")
data class PacketOutPluginMessage(
    val channel: Key,
    val content: ByteArray
) : Packet {

    constructor(reader: BinaryReader) : this(
        channel = reader.readKey(),
        content = reader.readAllBytes()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeKey(channel)
        writer.writeBytes(content)
    }
}
