package net.aquamine.server.packet.out.play

import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.coordinate.Positioning
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

@JvmRecord
data class PacketOutSetEntityVelocity(
    override val entityId: Int,
    val x: Short,
    val y: Short,
    val z: Short
) : EntityPacket {

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        x = reader.readShort(),
        y = reader.readShort(),
        z = reader.readShort()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeShort(x)
        writer.writeShort(y)
        writer.writeShort(z)
    }

    companion object {

        @JvmStatic
        fun fromEntity(entity: AquaEntity): PacketOutSetEntityVelocity {
            return PacketOutSetEntityVelocity(entity.id, encode(entity.velocity.x), encode(entity.velocity.y), encode(entity.velocity.z))
        }

        @JvmStatic
        private fun encode(value: Double): Short = Positioning.encodeVelocity(value)
    }
}
