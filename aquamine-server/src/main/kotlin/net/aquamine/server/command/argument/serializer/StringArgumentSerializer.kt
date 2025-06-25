package net.aquamine.server.command.argument.serializer

import com.mojang.brigadier.arguments.StringArgumentType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

/**
 * String argument types only serialize the type of string argument they are.
 * The possible values are:
 * * SINGLE_WORD - reads until it finds a space
 * * QUOTABLE_PHRASE - if the string starts with ", keeps reading until another
 *   " appears closing it off (can escape with \), otherwise behaves the same
 *   as SINGLE_WORD
 * * GREEDY_STRING - reads until there is nothing more to be read
 *
 * See [here](https://wiki.vg/Command_Data#brigadier:string)
 */
object StringArgumentSerializer : ArgumentSerializer<StringArgumentType> {

    private const val SINGLE_WORD_TYPE = 0
    private const val QUOTABLE_PHRASE_TYPE = 1
    private const val GREEDY_PHRASE_TYPE = 2

    override fun read(reader: BinaryReader): StringArgumentType = when (val type = reader.readVarInt()) {
        SINGLE_WORD_TYPE -> StringArgumentType.word()
        QUOTABLE_PHRASE_TYPE -> StringArgumentType.string()
        GREEDY_PHRASE_TYPE -> StringArgumentType.greedyString()
        else -> throw IllegalArgumentException("Cannot get type with ID $type!")
    }

    override fun write(writer: BinaryWriter, value: StringArgumentType) {
        writer.writeEnum(value.type)
    }
}
