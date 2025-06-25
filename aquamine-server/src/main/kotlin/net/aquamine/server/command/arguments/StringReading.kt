package net.aquamine.server.command.arguments

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.StringReader
import net.aquamine.server.util.Keys

/**
 * Some common utilities for StringReader.
 */
object StringReading {

    @JvmStatic
    fun readKeyString(reader: StringReader): String = readStringUntil(reader) { Keys.isValidCharacter(it) }

    @JvmStatic
    fun readNonSpaceString(reader: StringReader): String = readStringUntil(reader) { it != CommandDispatcher.ARGUMENT_SEPARATOR_CHAR }

    @JvmStatic
    private inline fun readStringUntil(reader: StringReader, condition: (Char) -> Boolean): String {
        val startPosition = reader.cursor
        while (reader.canRead() && condition(reader.peek())) {
            reader.skip()
        }
        return reader.string.substring(startPosition, reader.cursor)
    }

    @JvmStatic
    fun readOptionalDouble(reader: StringReader): Double {
        if (!reader.canRead() || reader.peek() == CommandDispatcher.ARGUMENT_SEPARATOR_CHAR) return 0.0
        return reader.readDouble()
    }
}
