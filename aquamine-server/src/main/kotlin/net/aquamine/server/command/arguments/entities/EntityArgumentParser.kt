package net.aquamine.server.command.arguments.entities

import com.mojang.brigadier.StringReader
import kotlinx.collections.immutable.persistentListOf
import net.aquamine.server.command.arguments.CommandExceptions
import net.aquamine.server.command.arguments.entities.EntityQuery.Selector

/**
 * Parses entity selectors.
 */
object EntityArgumentParser {

    const val SELECTOR_CHAR: Char = '@'
    private const val OPENING_BRACKET = '['
    private const val CLOSING_BRACKET = ']'
    private const val SELECTOR_SEPARATOR = ','
    private const val EXCLUSION = '!'
    private const val KEY_VALUE_SEPARATOR = '='

    private val UNKNOWN_SELECTOR = EntityArgumentExceptions.UNKNOWN_SELECTOR

    @JvmStatic
    fun parse(reader: StringReader, onlyPlayers: Boolean, singleTarget: Boolean): EntityQuery {
        val position = reader.cursor
        when (val operation = reader.read()) {
            Selector.NEAREST_PLAYER_CHAR -> return EntityQuery(Selector.NEAREST_PLAYER)
            Selector.ALL_ENTITIES_CHAR -> {
                if (singleTarget) CommandExceptions.resetAndThrow(reader, 0, EntityArgumentExceptions.TOO_MANY_ENTITIES.createWithContext(reader))
                if (onlyPlayers) CommandExceptions.resetAndThrow(reader, 0, EntityArgumentExceptions.ONLY_FOR_PLAYERS.createWithContext(reader))
                return parseMultiple(reader, Selector.ALL_ENTITIES)
            }
            Selector.RANDOM_PLAYER_CHAR -> return EntityQuery(Selector.RANDOM_PLAYER)
            Selector.ALL_PLAYERS_CHAR -> {
                if (singleTarget) CommandExceptions.resetAndThrow(reader, 0, EntityArgumentExceptions.TOO_MANY_PLAYERS.createWithContext(reader))
                return parseMultiple(reader, Selector.ALL_PLAYERS)
            }
            Selector.EXECUTOR_CHAR -> return EntityQuery(Selector.EXECUTOR)
            else -> CommandExceptions.resetAndThrow(reader, position, UNKNOWN_SELECTOR.createWithContext(reader, "$SELECTOR_CHAR$operation"))
        }
    }

    private fun parseMultiple(reader: StringReader, selector: Selector): EntityQuery {
        // If we have a [, we know that we have filters to parse
        if (reader.canRead() && reader.peek() == OPENING_BRACKET) {
            reader.skip()
            return EntityQuery(selector, parseArguments(reader))
        }
        return EntityQuery(selector)
    }

    @JvmStatic
    private fun parseArguments(reader: StringReader): List<EntityArgument> {
        reader.skipWhitespace()
        val arguments = persistentListOf<EntityArgument>().builder()
        while (reader.canRead() && reader.peek() != CLOSING_BRACKET) {
            reader.skipWhitespace()
            val markedPosition = reader.cursor
            val option = reader.readString()
            if (!EntityArguments.VALID.contains(option)) throw EntityArgumentExceptions.INVALID_OPTION.createWithContext(reader, option)

            reader.skipWhitespace()
            if (reader.canRead() && reader.peek() == KEY_VALUE_SEPARATOR) {
                reader.skip()
                reader.skipWhitespace()

                val exclude = reader.peek() == EXCLUSION
                if (exclude) reader.skip()
                arguments.add(EntityArgument(option, reader.readString(), exclude))

                reader.skipWhitespace()
                if (!reader.canRead()) continue

                if (reader.peek() == SELECTOR_SEPARATOR) {
                    reader.skip()
                    continue
                }
                if (reader.peek() != CLOSING_BRACKET) throw EntityArgumentExceptions.UNTERMINATED.createWithContext(reader)
                break
            }
            CommandExceptions.resetAndThrow(reader, markedPosition, EntityArgumentExceptions.VALUELESS.createWithContext(reader, option))
        }
        if (!reader.canRead()) throw EntityArgumentExceptions.UNTERMINATED.createWithContext(reader)
        reader.skip()
        return arguments.build()
    }
}
