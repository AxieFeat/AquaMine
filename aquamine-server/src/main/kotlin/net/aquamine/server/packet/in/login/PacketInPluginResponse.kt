package net.aquamine.server.packet.`in`.login

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.LoginPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
@Suppress("ArrayInDataClass")
data class PacketInPluginResponse(val messageId: Int, val data: ByteArray?) : InboundPacket<LoginPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readVarInt(), reader.readNullable(BinaryReader::readAllBytes))

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(messageId)
        writer.writeNullable(data, BinaryWriter::writeByteArray)
    }

    override fun handle(handler: LoginPacketHandler) {
        handler.handlePluginResponse(this)
    }
}
