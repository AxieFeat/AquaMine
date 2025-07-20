package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInQueryEntityTag(
    val transactionId: Int,
    val entityId: Int
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        transactionId = reader.readVarInt(),
        entityId = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(transactionId)
        writer.writeVarInt(entityId)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleEntityTagQuery(this)
    }
}
