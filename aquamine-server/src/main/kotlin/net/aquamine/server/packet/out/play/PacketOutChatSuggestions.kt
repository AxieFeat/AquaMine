package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutChatSuggestions(
    val action: Action,
    val entries: List<String>
) : Packet {

    constructor(reader: BinaryReader) : this(
        action = reader.readEnum(),
        entries = reader.readList { it.readString() }
    )

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(action)
        writer.writeCollection(entries, writer::writeString)
    }

    enum class Action {

        ADD,
        REMOVE,
        SET
    }
}
