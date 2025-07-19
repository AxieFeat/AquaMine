package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

// TODO Add advancements into server api
@JvmRecord
data class PacketInAdvancementTab(
    val action: AdvancementAction,
    val tabIdentifier: String?
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        action = reader.readEnum(),
        tabIdentifier = reader.readNullable { it.readString() }
    )

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(action)
        writer.writeNullable(tabIdentifier, BinaryWriter::writeString)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleAdvancementTab(this)
    }

    enum class AdvancementAction {
        OPENED_TAB, CLOSED_SCREEN
    }

}