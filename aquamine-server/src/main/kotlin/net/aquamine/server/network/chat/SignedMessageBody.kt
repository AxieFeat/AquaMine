package net.aquamine.server.network.chat

import com.google.common.primitives.Ints
import com.google.common.primitives.Longs
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.crypto.SignatureUpdater
import java.time.Instant

@JvmRecord
data class SignedMessageBody(val content: String, val timestamp: Instant, val salt: Long, val lastSeen: LastSeenMessages) {

    fun updateSignature(output: SignatureUpdater.Output) {
        output.update(Longs.toByteArray(salt))
        output.update(Longs.toByteArray(timestamp.epochSecond))
        val contentBytes = content.encodeToByteArray()
        output.update(Ints.toByteArray(contentBytes.size))
        output.update(contentBytes)
        lastSeen.updateSignature(output)
    }

    fun pack(signatureCache: MessageSignatureCache): Packed = Packed(content, timestamp, salt, lastSeen.pack(signatureCache))

    @JvmRecord
    data class Packed(val content: String, val timestamp: Instant, val salt: Long, val lastSeen: LastSeenMessages.Packed) : Writable {

        init {
            require(content.length <= MAX_MESSAGE_LENGTH) { "Message content too long! Max: $MAX_MESSAGE_LENGTH" }
        }

        constructor(reader: BinaryReader) : this(reader.readString(), reader.readInstant(), reader.readLong(), LastSeenMessages.Packed(reader))

        override fun write(writer: BinaryWriter) {
            writer.writeString(content)
            writer.writeInstant(timestamp)
            writer.writeLong(salt)
            lastSeen.write(writer)
        }
    }

    companion object {

        private const val MAX_MESSAGE_LENGTH = 256

        @JvmStatic
        fun unsigned(message: String): SignedMessageBody = SignedMessageBody(message, Instant.now(), 0L, LastSeenMessages.EMPTY)
    }
}
