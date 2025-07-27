package net.aquamine.server.packet.`in`.play

import net.aquamine.server.entity.player.RecipeBookType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInSetRecipeBookState(
    val bookType: RecipeBookType,
    val bookOpen: Boolean,
    val filterActive: Boolean
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        bookType = reader.readEnum(),
        bookOpen = reader.readBoolean(),
        filterActive = reader.readBoolean()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(bookType)
        writer.writeBoolean(bookOpen)
        writer.writeBoolean(filterActive)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleSetRecipeBookState(this)
    }
}
