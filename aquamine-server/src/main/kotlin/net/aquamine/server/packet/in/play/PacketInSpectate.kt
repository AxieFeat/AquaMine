package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket
import java.util.UUID

@JvmRecord
data class PacketInSpectate(
    val target: UUID
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        target = reader.readUUID()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeUUID(target)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleSpectate(this)
    }
}