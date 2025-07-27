package net.aquamine.server.packet.`in`.play

import net.aquamine.api.util.Vec3i
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInUpdateCommandBlock(
    val blockPosition: Vec3i,
    val command: String,
    val mode: Mode,
    val flags: Byte
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        blockPosition = reader.readBlockPos(),
        command = reader.readString(),
        mode = reader.readEnum(),
        flags = reader.readByte()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeBlockPos(blockPosition)
        writer.writeString(command)
        writer.writeEnum(mode)
        writer.writeByte(flags)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleUpdateCommandBlock(this)
    }

    enum class Mode {
        SEQUENCE, AUTO, REDSTONE
    }
}
