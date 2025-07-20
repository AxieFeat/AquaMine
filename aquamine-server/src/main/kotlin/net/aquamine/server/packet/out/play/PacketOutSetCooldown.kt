package net.aquamine.server.packet.out.play

import net.aquamine.server.item.AquaItemType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.registry.AquaRegistries

@JvmRecord
data class PacketOutSetCooldown(
    val item: AquaItemType,
    val cooldownTicks: Int
) : Packet {

    constructor(reader: BinaryReader) : this(
        item = reader.readById(AquaRegistries.ITEM)!!,
        cooldownTicks = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeId(AquaRegistries.ITEM, item)
        writer.writeVarInt(cooldownTicks)
    }
}
