package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInAbilities(val isFlying: Boolean) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readByte().toInt() and 2 != 0)

    override fun write(writer: BinaryWriter) {
        var flags = 0
        if (isFlying) flags = flags or 2
        writer.writeByte(flags.toByte())
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleAbilities(this)
    }
}
