package net.aquamine.server.entity.player

import net.kyori.adventure.text.Component
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.ComponentException
import net.aquamine.server.util.crypto.Encryption
import net.aquamine.server.util.crypto.SignatureValidator
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.security.PublicKey
import java.time.Duration
import java.time.Instant
import java.util.UUID

@JvmRecord
data class PlayerPublicKey(val data: Data) {

    fun createSignatureValidator(): SignatureValidator = SignatureValidator.from(data.key, Encryption.SIGNATURE_ALGORITHM)

    @JvmRecord
    @Suppress("ArrayInDataClass", "EqualsOrHashCode") // We want to keep the record-generated hash code.
    data class Data(val expiryTime: Instant, val key: PublicKey, val keySignature: ByteArray) : Writable {

        init {
            require(keySignature.size <= MAX_KEY_SIGNATURE_SIZE) { "Key signature too large! Max: $MAX_KEY_SIGNATURE_SIZE" }
        }

        constructor(reader: BinaryReader) : this(reader.readInstant(), reader.readPublicKey(), reader.readByteArray())

        override fun write(writer: BinaryWriter) {
            writer.writeInstant(expiryTime)
            writer.writePublicKey(key)
            writer.writeByteArray(keySignature)
        }

        fun validateSignature(validator: SignatureValidator, sessionId: UUID): Boolean = validator.validate(signedPayload(sessionId), keySignature)

        private fun signedPayload(sessionId: UUID): ByteArray {
            val encodedKey = key.encoded
            val result = ByteArray(24 + encodedKey.size) // 16 bytes for UUID (all UUIDs are 128 bits) + 8 bytes for expiry time millis as long
            val buffer = ByteBuffer.wrap(result).order(ByteOrder.BIG_ENDIAN)
            buffer.putLong(sessionId.mostSignificantBits).putLong(sessionId.leastSignificantBits).putLong(expiryTime.toEpochMilli()).put(encodedKey)
            return result
        }

        fun hasExpired(): Boolean = expiryTime.isBefore(Instant.now())

        fun hasExpired(duration: Duration): Boolean = expiryTime.plus(duration).isBefore(Instant.now())

        override fun equals(other: Any?): Boolean {
            return other is Data && expiryTime == other.expiryTime && key == other.key && keySignature.contentEquals(other.keySignature)
        }

        companion object {

            private const val MAX_KEY_SIGNATURE_SIZE = 4096
        }
    }

    class ValidationException(message: Component) : ComponentException(message)

    companion object {

        @JvmField
        val EXPIRED_KEY: Component = Component.translatable("multiplayer.disconnect.expired_public_key")
        private val INVALID_SIGNATURE = Component.translatable("multiplayer.disconnect.invalid_public_key_signature")

        @JvmStatic
        fun createValidated(validator: SignatureValidator, sessionId: UUID, data: Data, duration: Duration): PlayerPublicKey {
            if (data.hasExpired(duration)) throw ValidationException(EXPIRED_KEY)
            if (!data.validateSignature(validator, sessionId)) throw ValidationException(INVALID_SIGNATURE)
            return PlayerPublicKey(data)
        }
    }
}
