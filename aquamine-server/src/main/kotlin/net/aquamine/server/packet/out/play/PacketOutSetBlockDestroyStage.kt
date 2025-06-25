package net.aquamine.server.packet.out.play

import net.aquamine.api.util.Vec3i
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket

@JvmRecord
data class PacketOutSetBlockDestroyStage(override val entityId: Int, val position: Vec3i, val destroyStage: Byte) : EntityPacket {

    constructor(reader: BinaryReader) : this(reader.readVarInt(), reader.readBlockPos(), reader.readByte())

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeBlockPos(position)
        writer.writeByte(destroyStage)
    }
}
