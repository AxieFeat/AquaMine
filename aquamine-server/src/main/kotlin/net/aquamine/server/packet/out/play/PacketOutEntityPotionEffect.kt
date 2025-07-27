package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.potion.AquaPotionEffect
import xyz.axie.nbt.CompoundTag

@JvmRecord
data class PacketOutEntityPotionEffect(
    override val entityId: Int,
    val potion: AquaPotionEffect,
    val factorCodec: CompoundTag?
) : EntityPacket {

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        potion = reader.readPotion(),
        factorCodec = reader.readNullable { it.readNBT() }
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writePotion(potion)
        writer.writeNullable(factorCodec, BinaryWriter::writeNBT)
    }
}
