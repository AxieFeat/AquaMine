package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket

/**
 * Sent to indicate a status for an entity.
 */
@JvmRecord
data class PacketOutEntityEvent(override val entityId: Int, val event: Byte) : EntityPacket {

    constructor(reader: BinaryReader) : this(reader.readInt(), reader.readByte())

    override fun write(writer: BinaryWriter) {
        writer.writeInt(entityId)
        writer.writeByte(event)
    }
}
