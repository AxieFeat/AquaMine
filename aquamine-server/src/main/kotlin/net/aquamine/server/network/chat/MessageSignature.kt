package net.aquamine.server.network.chat

import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.crypto.SignatureUpdater
import net.aquamine.server.util.crypto.SignatureValidator
import java.nio.ByteBuffer
import java.util.Base64

@JvmRecord
data class MessageSignature(val bytes: ByteArray) {

    init {
        check(bytes.size == BYTES) { "Invalid message signature size! Expected $BYTES size, got ${bytes.size}!" }
    }

    fun verify(validator: SignatureValidator, updater: SignatureUpdater): Boolean = validator.validate(bytes, updater)

    fun asByteBuffer(): ByteBuffer? = ByteBuffer.wrap(bytes)

    fun pack(signatureCache: MessageSignatureCache): Packed {
        val packedId = signatureCache.pack(this)
        return if (packedId != Packed.FULL_SIGNATURE_ID) Packed(packedId) else Packed(this)
    }

    override fun equals(other: Any?): Boolean = this === other || other is MessageSignature && bytes.contentEquals(other.bytes)

    override fun hashCode(): Int = bytes.contentHashCode()

    override fun toString(): String = Base64.getEncoder().encodeToString(bytes)

    @JvmRecord
    data class Packed(val id: Int, val fullSignature: MessageSignature?) : Writable {

        constructor(fullSignature: MessageSignature) : this(FULL_SIGNATURE_ID, fullSignature)

        constructor(id: Int) : this(id, null)

        override fun write(writer: BinaryWriter) {
            writer.writeVarInt(id + 1)
            fullSignature?.let { write(writer, it) }
        }

        companion object {

            const val FULL_SIGNATURE_ID: Int = -1

            @JvmStatic
            fun read(reader: BinaryReader): Packed {
                val id = reader.readVarInt() - 1
                return if (id == FULL_SIGNATURE_ID) Packed(MessageSignature.read(reader)) else Packed(id)
            }
        }
    }

    companion object {

        private const val BYTES = 256

        @JvmStatic
        fun read(reader: BinaryReader): MessageSignature = MessageSignature(reader.readBytes(BYTES))

        @JvmStatic
        fun write(writer: BinaryWriter, signature: MessageSignature) {
            writer.writeBytes(signature.bytes)
        }
    }
}
