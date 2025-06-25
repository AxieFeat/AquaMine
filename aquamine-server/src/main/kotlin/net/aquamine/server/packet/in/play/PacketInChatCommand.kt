package net.aquamine.server.packet.`in`.play

import net.aquamine.server.command.argument.ArgumentSignatures
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.network.chat.LastSeenMessages
import net.aquamine.server.packet.InboundPacket
import java.time.Instant

@JvmRecord
data class PacketInChatCommand(
    val command: String,
    val timestamp: Instant,
    val salt: Long,
    val argumentSignatures: ArgumentSignatures,
    val lastSeenMessages: LastSeenMessages.Update
) : InboundPacket<PlayPacketHandler> {

    init {
        require(command.length <= MAX_COMMAND_LENGTH) { "Command is too long! Max: $MAX_COMMAND_LENGTH" }
    }

    constructor(reader: BinaryReader) : this(reader.readString(), reader.readInstant(), reader.readLong(), ArgumentSignatures(reader),
        LastSeenMessages.Update(reader))

    override fun write(writer: BinaryWriter) {
        writer.writeString(command)
        writer.writeInstant(timestamp)
        writer.writeLong(salt)
        argumentSignatures.write(writer)
        lastSeenMessages.write(writer)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleChatCommand(this)
    }

    companion object {

        private const val MAX_COMMAND_LENGTH = 256
    }
}
