package net.aquamine.server.packet.out.play

import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutSetContainerSlot(val id: Byte, val stateId: Int, val slot: Short, val item: KryptonItemStack) : Packet {

    constructor(reader: BinaryReader) : this(reader.readByte(), reader.readVarInt(), reader.readShort(), reader.readItem())

    override fun write(writer: BinaryWriter) {
        writer.writeByte(id)
        writer.writeVarInt(stateId)
        writer.writeShort(slot)
        writer.writeItem(item)
    }
}
