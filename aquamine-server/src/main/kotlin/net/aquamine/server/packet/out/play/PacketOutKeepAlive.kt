package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

/**
 * This is a message to the client to say "hey, I'm still here by the way", so the client doesn't just assume
 * we've timed out and disconnect.
 *
 * @param keepAliveId a unique ID for the keep alive. Vanilla calls this the challenge, and this is generally
 * [System.currentTimeMillis]
 */
@JvmRecord
data class PacketOutKeepAlive(val keepAliveId: Long) : Packet {

    constructor(reader: BinaryReader) : this(reader.readLong())

    override fun write(writer: BinaryWriter) {
        writer.writeLong(keepAliveId)
    }
}
