package net.aquamine.server.packet.out.play

import net.aquamine.api.entity.Entity
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket

@JvmRecord
@Suppress("ArrayInDataClass")
data class PacketOutSetPassengers(
    override val entityId: Int,
    val passengers: IntArray
) : EntityPacket {

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        passengers = reader.readVarIntArray()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeVarIntArray(passengers)
    }

    companion object {

        @JvmStatic
        fun fromEntity(entity: AquaEntity, passengers: List<Entity>): PacketOutSetPassengers {
            return PacketOutSetPassengers(entity.id, toIdArray(passengers))
        }

        @JvmStatic
        private fun toIdArray(entities: List<Entity>): IntArray = IntArray(entities.size) { entities[it].id }
    }
}
