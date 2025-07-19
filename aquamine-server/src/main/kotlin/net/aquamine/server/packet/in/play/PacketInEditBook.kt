package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInEditBook(
    val slot: Int,
    val pages: List<String>,
    val title: String?
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        slot = reader.readVarInt(),
        pages = reader.readList { it.readString() },
        title = reader.readNullable { reader.readString() }
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(slot)
        writer.writeCollection(pages) { writer.writeString(it) }
        writer.writeNullable(title, BinaryWriter::writeString)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleEditBook(this)
    }
}