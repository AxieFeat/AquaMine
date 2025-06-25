package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.util.math.Maths

@JvmRecord
data class PacketOutSetHeadRotation(override val entityId: Int, val headYaw: Byte) : EntityPacket {

    constructor(entityId: Int, headYaw: Float) : this(entityId, Maths.floor(headYaw * 256F / 360F).toByte())

    constructor(reader: BinaryReader) : this(reader.readVarInt(), reader.readByte())

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeByte(headYaw)
    }
}
