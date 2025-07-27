package net.aquamine.server.packet.`in`.play

import net.aquamine.api.util.Vec3i
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInUpdateSign(
    val blockPosition: Vec3i,
    val lines: List<String>
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        blockPosition = reader.readBlockPos(),
        lines = reader.readList { reader.readString() }
    )

    override fun write(writer: BinaryWriter) {
        writer.writeBlockPos(blockPosition)
        writer.writeCollection(lines) { writer.writeString(it) }
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleUpdateSign(this)
    }
}
