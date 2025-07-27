package net.aquamine.server.packet.`in`.play

import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInClickContainer(
    val containerId: Byte,
    val stateId: Int,
    val slot: Short,
    val button: Byte,
    val clickType: ClickType,
    val changedSlot: List<ChangedSlot>,
    val clickedItem: AquaItemStack,
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        containerId = reader.readByte(),
        stateId = reader.readVarInt(),
        slot = reader.readShort(),
        button = reader.readByte(),
        clickType = reader.readEnum(),
        changedSlot = reader.readCollection(::ArrayList, ::ChangedSlot),
        clickedItem = reader.readItem()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeByte(containerId)
        writer.writeInt(stateId)
        writer.writeShort(slot)
        writer.writeByte(button)
        writer.writeEnum(clickType)
        writer.writeCollection(changedSlot) { it.write(writer) }
        writer.writeItem(clickedItem)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleClickContainer(this)
    }

    @JvmRecord
    data class ChangedSlot(val slot: Short, val item: AquaItemStack) : Writable {

        constructor(reader: BinaryReader) : this(reader.readShort(), reader.readItem())

        override fun write(writer: BinaryWriter) {
            writer.writeShort(slot)
            writer.writeItem(item)
        }
    }

    enum class ClickType {
        PICKUP,
        QUICK_MOVE,
        SWAP,
        CLONE,
        THROW,
        QUICK_CRAFT,
        PICKUP_ALL
    }
}
