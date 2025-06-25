package net.aquamine.server.packet.`in`.play

import net.aquamine.api.resource.ResourcePack.Status
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInResourcePack(val status: Status) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readEnum<Status>())

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(status)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleResourcePack(this)
    }
}
