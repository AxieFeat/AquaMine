package net.aquamine.server.packet.out.play

import net.kyori.adventure.key.Key
import net.aquamine.server.entity.player.RecipeBookSettings
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutUpdateRecipeBook(
    val action: Action,
    val recipes: List<Key>,
    val toHighlight: List<Key>,
    val settings: RecipeBookSettings
) : Packet {

    constructor(reader: BinaryReader) : this(reader, reader.readEnum(), RecipeBookSettings.read(reader))

    private constructor(reader: BinaryReader, action: Action, settings: RecipeBookSettings) : this(action, reader.readList(BinaryReader::readKey),
        if (action == Action.INIT) reader.readList(BinaryReader::readKey) else emptyList(), settings)

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(action)
        settings.write(writer)
        writer.writeCollection(recipes, writer::writeKey)
        if (action == Action.INIT) writer.writeCollection(toHighlight, writer::writeKey)
    }

    enum class Action {

        INIT,
        ADD,
        REMOVE
    }
}
