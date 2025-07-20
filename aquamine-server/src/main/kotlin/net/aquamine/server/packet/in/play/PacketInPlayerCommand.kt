package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInPlayerCommand(
    val id: Int,
    val action: Action,
    val data: Int
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        id = reader.readVarInt(),
        action = reader.readEnum<Action>(),
        data = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(id)
        writer.writeEnum(action)
        writer.writeVarInt(data)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handlePlayerCommand(this)
    }

    enum class Action {

        START_SNEAKING,
        STOP_SNEAKING,
        STOP_SLEEPING,
        START_SPRINTING,
        STOP_SPRINTING,
        START_HORSE_JUMP,
        STOP_HORSE_JUMP,
        OPEN_INVENTORY,
        START_GLIDING
    }
}
