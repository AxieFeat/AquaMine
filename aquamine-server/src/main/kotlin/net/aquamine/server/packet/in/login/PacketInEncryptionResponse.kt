package net.aquamine.server.packet.`in`.login

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.LoginPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
@Suppress("ArrayInDataClass")
data class PacketInEncryptionResponse(val secret: ByteArray, val verifyToken: ByteArray) : InboundPacket<LoginPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readByteArray(), reader.readByteArray())

    override fun write(writer: BinaryWriter) {
        writer.writeByteArray(secret)
        writer.writeByteArray(verifyToken)
    }

    override fun handle(handler: LoginPacketHandler) {
        handler.handleEncryptionResponse(this)
    }
}
