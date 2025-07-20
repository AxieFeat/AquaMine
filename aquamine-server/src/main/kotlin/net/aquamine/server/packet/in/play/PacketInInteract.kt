package net.aquamine.server.packet.`in`.play

import net.aquamine.api.entity.Hand
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInInteract(
    val entityId: Int,
    val action: Action,
    val sneaking: Boolean
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        action = reader.readEnum<ActionType>().read(reader),
        sneaking = reader.readBoolean()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeEnum(action.type())
        action.write(writer)
        writer.writeBoolean(sneaking)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleInteract(this)
    }

    sealed interface Action : Writable {

        fun type(): ActionType
    }

    @JvmRecord
    data class InteractAction(val hand: Hand) : Action {

        constructor(reader: BinaryReader) : this(reader.readEnum<Hand>())

        override fun type(): ActionType = ActionType.INTERACT

        override fun write(writer: BinaryWriter) {
            writer.writeEnum(hand)
        }
    }

    object AttackAction : Action {

        override fun type(): ActionType = ActionType.ATTACK

        override fun write(writer: BinaryWriter) {
            // Nothing to write for the attack action
        }
    }

    @JvmRecord
    data class InteractAtAction(val x: Float, val y: Float, val z: Float, val hand: Hand) : Action {

        constructor(reader: BinaryReader) : this(reader.readFloat(), reader.readFloat(), reader.readFloat(), reader.readEnum<Hand>())

        override fun type(): ActionType = ActionType.INTERACT_AT

        override fun write(writer: BinaryWriter) {
            writer.writeFloat(x)
            writer.writeFloat(y)
            writer.writeFloat(z)
            writer.writeEnum(hand)
        }
    }

    enum class ActionType(private val reader: Reader) {

        INTERACT({ InteractAction(it) }),
        ATTACK({ AttackAction }),
        INTERACT_AT({ InteractAtAction(it) });

        fun read(reader: BinaryReader): Action = this.reader.read(reader)

        private fun interface Reader {

            fun read(reader: BinaryReader): Action
        }
    }
}
