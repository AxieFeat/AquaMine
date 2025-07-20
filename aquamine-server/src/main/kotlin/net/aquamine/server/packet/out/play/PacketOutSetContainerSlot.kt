package net.aquamine.server.packet.out.play

import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutSetContainerSlot(
    val id: Byte,
    val stateId: Int,
    val slot: Short,
    val item: AquaItemStack
) : Packet {

    constructor(reader: BinaryReader) : this(
        id = reader.readByte(),
        stateId = reader.readVarInt(),
        slot = reader.readShort(),
        item = reader.readItem()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeByte(id)
        writer.writeVarInt(stateId)
        writer.writeShort(slot)
        writer.writeItem(item)
    }
}
