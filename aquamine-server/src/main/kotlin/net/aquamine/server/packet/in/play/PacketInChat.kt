package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.chat.LastSeenMessages
import net.aquamine.server.network.chat.MessageSignature
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket
import java.time.Instant

@JvmRecord
data class PacketInChat(
    val message: String,
    val timestamp: Instant,
    val salt: Long,
    val signature: MessageSignature?,
    val lastSeenMessages: LastSeenMessages.Update
) : InboundPacket<PlayPacketHandler> {

    init {
        require(message.length <= MAX_MESSAGE_LENGTH) { "Message too long! Max: $MAX_MESSAGE_LENGTH" }
    }

    constructor(reader: BinaryReader) : this(
        message = reader.readString(),
        timestamp = reader.readInstant(),
        salt = reader.readLong(),
        signature = reader.readNullable(MessageSignature::read),
        lastSeenMessages = LastSeenMessages.Update(reader)
    )

    override fun write(writer: BinaryWriter) {
        writer.writeString(message)
        writer.writeInstant(timestamp)
        writer.writeLong(salt)
        writer.writeNullable(signature, MessageSignature::write)
        lastSeenMessages.write(writer)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleChat(this)
    }

    companion object {

        private const val MAX_MESSAGE_LENGTH = 256
    }
}
