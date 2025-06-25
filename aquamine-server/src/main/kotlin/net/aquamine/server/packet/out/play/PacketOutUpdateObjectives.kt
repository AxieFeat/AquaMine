package net.aquamine.server.packet.out.play

import net.kyori.adventure.text.Component
import net.aquamine.api.scoreboard.Objective
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

/**
 * Tells the client to perform an action to an objective for a scoreboard.
 */
@JvmRecord
data class PacketOutUpdateObjectives(val name: String, val action: Byte, val displayName: Component, val renderType: Int) : Packet {

    init {
        require(name.length <= 16) { "Objective name too long! Max: 16" }
    }

    constructor(reader: BinaryReader) : this(reader, reader.readString(), reader.readByte())

    private constructor(reader: BinaryReader, name: String, action: Byte) : this(
        name,
        action,
        if (action != Actions.REMOVE) reader.readComponent() else Component.empty(),
        if (action != Actions.REMOVE) reader.readVarInt() else 0
    )

    override fun write(writer: BinaryWriter) {
        writer.writeString(name)
        writer.writeByte(action)
        if (action != Actions.REMOVE) {
            writer.writeComponent(displayName)
            writer.writeVarInt(renderType)
        }
    }

    object Actions {

        const val CREATE: Byte = 0
        const val REMOVE: Byte = 1
        const val UPDATE_TEXT: Byte = 2
    }

    companion object {

        @JvmStatic
        fun create(objective: Objective): PacketOutUpdateObjectives = createOrRemove(objective, Actions.CREATE)

        @JvmStatic
        fun remove(objective: Objective): PacketOutUpdateObjectives = createOrRemove(objective, Actions.REMOVE)

        @JvmStatic
        private fun createOrRemove(objective: Objective, action: Byte): PacketOutUpdateObjectives {
            return PacketOutUpdateObjectives(objective.name, action, objective.displayName, objective.renderType.ordinal)
        }

        @JvmStatic
        fun updateText(objective: Objective): PacketOutUpdateObjectives {
            return PacketOutUpdateObjectives(objective.name, Actions.UPDATE_TEXT, objective.displayName, objective.renderType.ordinal)
        }
    }
}
