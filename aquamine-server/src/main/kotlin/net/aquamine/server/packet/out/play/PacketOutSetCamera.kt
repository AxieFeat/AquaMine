package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket

@JvmRecord
data class PacketOutSetCamera(
    override val entityId: Int
) : EntityPacket {

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
    }
}
