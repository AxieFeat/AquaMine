package net.aquamine.server.packet.`in`.play

import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInSetCreativeModeSlot(val slot: Short, val clickedItem: KryptonItemStack) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readShort(), reader.readItem())

    override fun write(writer: BinaryWriter) {
        writer.writeShort(slot)
        writer.writeItem(clickedItem)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleSetCreativeModeSlot(this)
    }
}
