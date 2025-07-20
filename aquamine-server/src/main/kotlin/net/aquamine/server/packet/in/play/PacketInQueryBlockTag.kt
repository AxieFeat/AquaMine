package net.aquamine.server.packet.`in`.play

import net.aquamine.api.util.Vec3i
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInQueryBlockTag(
    val transactionId: Int,
    val blockPosition: Vec3i
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        transactionId = reader.readVarInt(),
        blockPosition = reader.readBlockPos()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(transactionId)
        writer.writeBlockPos(blockPosition)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleBlockTagQuery(this)
    }
}
