package net.aquamine.server.packet.`in`.login

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.LoginPacketHandler
import net.aquamine.server.packet.InboundPacket
import java.util.UUID

@JvmRecord
data class PacketInLoginStart(val name: String, val profileId: UUID?) : InboundPacket<LoginPacketHandler> {

    init {
        require(name.length <= 16) { "Name must be 16 characters or less!" }
    }

    constructor(reader: BinaryReader) : this(reader.readString(), reader.readNullable(BinaryReader::readUUID))

    override fun write(writer: BinaryWriter) {
        writer.writeString(name)
        writer.writeNullable(profileId, BinaryWriter::writeUUID)
    }

    override fun handle(handler: LoginPacketHandler) {
        handler.handleLoginStart(this)
    }
}
