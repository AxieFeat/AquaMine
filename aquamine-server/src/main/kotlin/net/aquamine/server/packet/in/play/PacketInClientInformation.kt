package net.aquamine.server.packet.`in`.play

import net.aquamine.api.entity.MainHand
import net.aquamine.api.entity.player.ChatVisibility
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInClientInformation(
    val locale: String,
    val viewDistance: Byte,
    val chatVisibility: ChatVisibility,
    val chatColors: Boolean,
    val skinSettings: Byte,
    val mainHand: MainHand,
    val filterText: Boolean,
    val allowsListing: Boolean
) : InboundPacket<PlayPacketHandler> {

    init {
        require(locale.length <= LOCALE_MAX_LENGTH) { "Locale too long! Max: $LOCALE_MAX_LENGTH" }
    }

    constructor(reader: BinaryReader) : this(
        locale = reader.readString(),
        viewDistance = reader.readByte(),
        chatVisibility = reader.readEnum(),
        chatColors = reader.readBoolean(),
        skinSettings = reader.readByte(),
        mainHand = reader.readEnum(),
        filterText = reader.readBoolean(),
        allowsListing = reader.readBoolean()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeString(locale)
        writer.writeByte(viewDistance)
        writer.writeEnum(chatVisibility)
        writer.writeBoolean(chatColors)
        writer.writeByte(skinSettings)
        writer.writeEnum(mainHand)
        writer.writeBoolean(filterText)
        writer.writeBoolean(allowsListing)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleClientInformation(this)
    }

    companion object {

        private const val LOCALE_MAX_LENGTH = 16
    }
}
