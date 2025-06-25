package net.aquamine.server.packet.out.status

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

/**
 * Response to the client's [ping][net.aquamine.server.packet.in.status.PacketInPingRequest] packet.
 */
@JvmRecord
data class PacketOutPingResponse(val value: Long) : Packet {

    constructor(reader: BinaryReader) : this(reader.readLong())

    override fun write(writer: BinaryWriter) {
        writer.writeLong(value)
    }
}
