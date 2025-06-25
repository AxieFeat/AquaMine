package net.aquamine.server.network.chat

import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.crypto.Crypto
import net.aquamine.server.util.uuid.UUIDUtil
import java.time.Instant
import java.util.UUID

@JvmRecord
data class MessageSigner(val profileId: UUID, val timestamp: Instant, val salt: Long) : Writable {

    constructor(reader: BinaryReader) : this(reader.readUUID(), reader.readInstant(), reader.readLong())

    override fun write(writer: BinaryWriter) {
        writer.writeUUID(profileId)
        writer.writeInstant(timestamp)
        writer.writeLong(salt)
    }

    fun isSystem(): Boolean = profileId == UUIDUtil.NIL_UUID

    companion object {

        @JvmStatic
        fun create(profileId: UUID): MessageSigner = MessageSigner(profileId, Instant.now(), Crypto.SaltSupplier.getLong())

        @JvmStatic
        fun system(): MessageSigner = create(UUIDUtil.NIL_UUID)
    }
}
