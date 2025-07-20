package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket

@JvmRecord
data class PacketOutAnimation(
    override val entityId: Int,
    val animation: Byte
) : EntityPacket {

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        animation = reader.readByte()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeByte(animation)
    }
}
