package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInClientCommand(val action: Action) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readEnum<Action>())

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(action)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleClientCommand(this)
    }

    enum class Action {

        PERFORM_RESPAWN,
        REQUEST_STATS
    }
}
