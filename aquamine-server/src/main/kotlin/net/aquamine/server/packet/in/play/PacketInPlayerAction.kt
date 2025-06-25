package net.aquamine.server.packet.`in`.play

import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInPlayerAction(
    val action: Action,
    val position: Vec3i,
    val direction: Direction,
    val sequence: Int
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readEnum<Action>(), reader.readBlockPos(), reader.readEnum(), reader.readVarInt())

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(action)
        writer.writeBlockPos(position)
        writer.writeEnum(direction)
        writer.writeVarInt(sequence)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handlePlayerAction(this)
    }

    enum class Action {

        START_DIGGING,
        CANCEL_DIGGING,
        FINISH_DIGGING,
        DROP_ITEM_STACK,
        DROP_ITEM,
        RELEASE_USE_ITEM,
        SWAP_ITEM_IN_HAND
    }
}
