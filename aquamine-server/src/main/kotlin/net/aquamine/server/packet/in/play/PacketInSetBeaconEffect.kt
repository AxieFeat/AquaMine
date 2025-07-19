package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

// TODO Implement potion system
@JvmRecord
data class PacketInSetBeaconEffect(
    val todo: String? = null
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this()

    override fun write(writer: BinaryWriter) {

    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleSetBeaconEffect(this)
    }
}