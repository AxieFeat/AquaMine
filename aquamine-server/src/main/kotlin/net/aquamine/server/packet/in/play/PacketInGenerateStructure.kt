package net.aquamine.server.packet.`in`.play

import net.aquamine.api.util.Vec3i
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInGenerateStructure(
    val blockPosition: Vec3i,
    val level: Int,
    val keepJigsaws: Boolean,
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        blockPosition = reader.readBlockPos(),
        level = reader.readVarInt(),
        keepJigsaws = reader.readBoolean()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeBlockPos(blockPosition)
        writer.writeVarInt(level)
        writer.writeBoolean(keepJigsaws)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleGenerateStructure(this)
    }
}