package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInPlayerInput(
    val sideways: Float,
    val forward: Float,
    val flags: Byte
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        sideways = reader.readFloat(),
        forward = reader.readFloat(),
        flags = reader.readByte()
    )

    fun isJumping(): Boolean = flags.toInt() and 1 > 0

    fun isSneaking(): Boolean = flags.toInt() and 2 > 0

    override fun write(writer: BinaryWriter) {
        writer.writeFloat(sideways)
        writer.writeFloat(forward)
        writer.writeByte(flags)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handlePlayerInput(this)
    }
}
