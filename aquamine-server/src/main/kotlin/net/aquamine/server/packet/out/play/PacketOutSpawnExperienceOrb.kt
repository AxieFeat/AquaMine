package net.aquamine.server.packet.out.play

import net.aquamine.server.entity.AquaExperienceOrb
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket

@JvmRecord
data class PacketOutSpawnExperienceOrb(
    override val entityId: Int,
    val x: Double,
    val y: Double,
    val z: Double,
    val count: Int
) : EntityPacket {

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        x = reader.readDouble(),
        y = reader.readDouble(),
        z = reader.readDouble(),
        count = reader.readShort().toInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeDouble(x)
        writer.writeDouble(y)
        writer.writeDouble(z)
        writer.writeShort(count.toShort())
    }

    companion object {

        @JvmStatic
        fun create(orb: AquaExperienceOrb): PacketOutSpawnExperienceOrb {
            return PacketOutSpawnExperienceOrb(orb.id, orb.position.x, orb.position.y, orb.position.z, orb.count)
        }
    }
}
