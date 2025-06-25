package net.aquamine.server.packet.`in`.play

import net.kyori.adventure.key.Key
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
@Suppress("ArrayInDataClass")
data class PacketInPluginMessage(val channel: Key, val data: ByteArray) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readKey(), reader.readAllBytes())

    override fun write(writer: BinaryWriter) {
        writer.writeKey(channel)
        writer.writeByteArray(data)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handlePluginMessage(this)
    }
}
