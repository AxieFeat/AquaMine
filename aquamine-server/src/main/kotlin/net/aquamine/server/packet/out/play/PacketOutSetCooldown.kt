package net.aquamine.server.packet.out.play

import net.aquamine.server.item.KryptonItemType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.registry.KryptonRegistries

@JvmRecord
data class PacketOutSetCooldown(val item: KryptonItemType, val cooldownTicks: Int) : Packet {

    constructor(reader: BinaryReader) : this(reader.readById(KryptonRegistries.ITEM)!!, reader.readVarInt())

    override fun write(writer: BinaryWriter) {
        writer.writeId(KryptonRegistries.ITEM, item)
        writer.writeVarInt(cooldownTicks)
    }
}
