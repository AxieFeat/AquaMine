package net.aquamine.server.packet.out.play

import com.mojang.brigadier.context.StringRange
import com.mojang.brigadier.suggestion.Suggestion
import com.mojang.brigadier.suggestion.Suggestions
import net.kyori.adventure.text.Component
import net.aquamine.api.adventure.AdventureMessage
import net.aquamine.server.adventure.AquaAdventure
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

/**
 * Sent by the server as a response to the
 * [tab complete][net.aquamine.server.packet.in.play.PacketInCommandSuggestionsRequest].
 * Contains all of the matches that the server got for the command provided by
 * the request packet.
 *
 * @param id The unique ID sent by the client to identify this request.
 * @param suggestions The suggestions for the client.
 */
@JvmRecord
data class PacketOutCommandSuggestionsResponse(
    val id: Int,
    val suggestions: Suggestions
) : Packet {

    constructor(reader: BinaryReader) : this(
        id = reader.readVarInt(),
        suggestions = readSuggestions(reader)
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(id)
        writer.writeVarInt(suggestions.range.start)
        writer.writeVarInt(suggestions.range.length)
        writer.writeCollection(suggestions.list) {
            writer.writeString(it.text)
            writer.writeNullable(it.tooltip) { buf, tooltip ->
                val message = if (tooltip is AdventureMessage) tooltip.asComponent() else Component.text(tooltip.string)
                buf.writeComponent(message)
            }
        }
    }

    companion object {

        @JvmStatic
        private fun readSuggestions(reader: BinaryReader): Suggestions {
            val start = reader.readVarInt()
            val length = reader.readVarInt()
            val range = StringRange.between(start, start + length)
            val results = reader.readList {
                Suggestion(range, reader.readString(), reader.readNullable { AquaAdventure.asMessage(reader.readComponent()) })
            }
            return Suggestions(range, results)
        }
    }
}
