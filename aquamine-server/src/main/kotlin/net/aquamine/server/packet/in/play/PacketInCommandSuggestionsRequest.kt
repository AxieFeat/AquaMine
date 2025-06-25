package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInCommandSuggestionsRequest(val id: Int, val command: String) : InboundPacket<PlayPacketHandler> {

    init {
        require(command.length <= COMMAND_MAX_LENGTH) { "Command too long! Max: $COMMAND_MAX_LENGTH" }
    }

    constructor(reader: BinaryReader) : this(reader.readVarInt(), reader.readString())

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(id)
        writer.writeString(command)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleCommandSuggestionsRequest(this)
    }

    companion object {

        private const val COMMAND_MAX_LENGTH = 32500
    }
}
