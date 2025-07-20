package net.aquamine.server.packet.out.play

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.aquamine.api.scoreboard.Score
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutUpdateScore(
    val name: String,
    val action: Int,
    val objectiveName: String?,
    val score: Int
) : Packet {

    init {
        require(name.length <= MAX_NAME_LENGTH) { "Name too long! Max: $MAX_NAME_LENGTH" }
        require(objectiveName == null || objectiveName.length <= MAX_OBJECTIVE_NAME_LENGTH) {
            "Objective name too long! Max: $MAX_OBJECTIVE_NAME_LENGTH"
        }
    }

    constructor(reader: BinaryReader) : this(
        reader = reader,
        name = reader.readString(),
        action = reader.readVarInt(),
        objectiveName = reader.readString().ifEmpty { null }
    )

    private constructor(reader: BinaryReader, name: String, action: Int, objectiveName: String?) : this(
        name = name,
        action = action,
        objectiveName = objectiveName,
        score = if (action != Actions.REMOVE) reader.readVarInt() else 0
    )

    override fun write(writer: BinaryWriter) {
        writer.writeString(name)
        writer.writeVarInt(action)
        writer.writeString(objectiveName ?: "")
        if (action != Actions.REMOVE) writer.writeVarInt(score)
    }

    object Actions {

        const val CREATE_OR_UPDATE: Int = 0
        const val REMOVE: Int = 1
    }

    companion object {

        private const val MAX_NAME_LENGTH = 40
        private const val MAX_OBJECTIVE_NAME_LENGTH = 16

        @JvmStatic
        fun createOrUpdate(score: Score): PacketOutUpdateScore {
            return PacketOutUpdateScore(toLegacyString(score.member), Actions.CREATE_OR_UPDATE, score.objective.name, score.score)
        }

        @JvmStatic
        fun remove(member: Component, objectiveName: String?, score: Int): PacketOutUpdateScore {
            return PacketOutUpdateScore(toLegacyString(member), Actions.REMOVE, objectiveName, score)
        }

        @JvmStatic
        private fun toLegacyString(input: Component): String = LegacyComponentSerializer.legacySection().serialize(input)
    }
}
