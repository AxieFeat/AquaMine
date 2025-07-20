package net.aquamine.server.packet.out.play

import net.kyori.adventure.text.Component
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutResourcePack(
    val uri: String,
    val hash: String,
    val forced: Boolean,
    val prompt: Component?
) : Packet {

    constructor(reader: BinaryReader) : this(
        uri = reader.readString(),
        hash =  reader.readString(),
        forced = reader.readBoolean(),
        prompt = reader.readNullable(BinaryReader::readComponent)
    )

    override fun write(writer: BinaryWriter) {
        writer.writeString(uri)
        writer.writeString(hash)
        writer.writeBoolean(forced)
        writer.writeNullable(prompt, BinaryWriter::writeComponent)
    }
}
