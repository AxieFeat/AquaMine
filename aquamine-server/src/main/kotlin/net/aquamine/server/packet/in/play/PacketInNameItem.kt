package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInNameItem(
    val itemName: String,
) : InboundPacket<PlayPacketHandler> {

    init {
        require(itemName.length <= NAME_MAX_LENGTH) { "Name of item too long! Max: $NAME_MAX_LENGTH" }
    }

    constructor(reader: BinaryReader) : this(
        itemName = reader.readString()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeString(itemName)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleNameItem(this)
    }

    companion object {

        private const val NAME_MAX_LENGTH = 256
    }
}
