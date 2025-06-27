package net.aquamine.server.packet.out.play

import net.aquamine.server.inventory.AquaPlayerInventory
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutSetContainerContent(val id: Byte, val stateId: Int, val items: List<AquaItemStack>, val heldItem: AquaItemStack) : Packet {

    constructor(reader: BinaryReader) : this(reader.readByte(), reader.readVarInt(), reader.readList(BinaryReader::readItem), reader.readItem())

    override fun write(writer: BinaryWriter) {
        writer.writeByte(id)
        writer.writeVarInt(stateId)
        writer.writeCollection(items, writer::writeItem)
        writer.writeItem(heldItem)
    }

    companion object {

        @JvmStatic
        fun fromPlayerInventory(inventory: AquaPlayerInventory): PacketOutSetContainerContent =
            PacketOutSetContainerContent(inventory.id.toByte(), inventory.incrementStateId(), inventory.items, inventory.mainHand)
    }
}
