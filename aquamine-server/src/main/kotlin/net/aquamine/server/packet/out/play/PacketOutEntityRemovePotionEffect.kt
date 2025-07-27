package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.potion.AquaPotionType
import net.aquamine.server.registry.AquaRegistries

@JvmRecord
data class PacketOutEntityRemovePotionEffect(
    override val entityId: Int,
    val potion: AquaPotionType
) : EntityPacket {

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        potion = AquaRegistries.POTION_TYPE.get(reader.readVarInt() - 1)!!
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeVarInt(AquaRegistries.POTION_TYPE.getId(potion) + 1)
    }
}
