package net.aquamine.server.packet.`in`.status

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.StatusPacketHandler
import net.aquamine.server.packet.InboundPacket

/**
 * Sent when the client pings the server.
 */
@JvmRecord
data class PacketInPingRequest(
    val payload: Long
) : InboundPacket<StatusPacketHandler> {

    constructor(reader: BinaryReader) : this(
        payload = reader.readLong()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeLong(payload)
    }

    override fun handle(handler: StatusPacketHandler) {
        handler.handlePing(this)
    }
}
