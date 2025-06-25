package net.aquamine.server.packet.out.login

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

/**
 * Sent to instruct the client that we wish to encrypt this connection. The
 * client is provided with our public key, so they can use it to encrypt the
 * shared secret, and a verify token, to attempt to ensure the connection
 * hasn't been tampered with (no hackers listening in).
 */
@JvmRecord
@Suppress("ArrayInDataClass")
data class PacketOutEncryptionRequest(val serverId: String, val publicKey: ByteArray, val verifyToken: ByteArray) : Packet {

    init {
        require(serverId.length <= MAX_SERVER_ID_LENGTH) { "Server ID too long! Max: $MAX_SERVER_ID_LENGTH" }
    }

    constructor(reader: BinaryReader) : this(reader.readString(), reader.readByteArray(), reader.readByteArray())

    override fun write(writer: BinaryWriter) {
        writer.writeString(serverId)
        writer.writeByteArray(publicKey)
        writer.writeByteArray(verifyToken)
    }

    companion object {

        private const val MAX_SERVER_ID_LENGTH = 20

        @JvmStatic
        fun create(publicKey: ByteArray, verifyToken: ByteArray): PacketOutEncryptionRequest = PacketOutEncryptionRequest("", publicKey, verifyToken)
    }
}
