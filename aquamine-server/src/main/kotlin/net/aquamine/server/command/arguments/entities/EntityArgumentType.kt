package net.aquamine.server.command.arguments.entities

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.entity.player.AquaPlayer

/**
 * An argument type that parses an entity selector, such as `@a` or `@p`.
 *
 * @param onlyPlayers whether the selector will only parse player entities
 * @param singleTarget whether the selector will only parse a single entity
 */
@JvmRecord
data class EntityArgumentType(val onlyPlayers: Boolean, val singleTarget: Boolean) : ArgumentType<EntityQuery> {

    override fun parse(reader: StringReader): EntityQuery {
        if (reader.canRead() && reader.peek() == EntityArgumentParser.SELECTOR_CHAR) {
            reader.skip()
            if (!reader.canRead()) throw EntityArgumentExceptions.MISSING_SELECTOR.createWithContext(reader)
            return EntityArgumentParser.parse(reader, onlyPlayers, singleTarget)
        }
        val input = reader.readString()
        if (PLAYER_NAME_REGEX.matches(input)) return EntityQuery(EntityQuery.Selector.PLAYER, input)
        return EntityQuery(EntityQuery.Selector.UNKNOWN)
    }

    override fun getExamples(): Collection<String> = EXAMPLES

    companion object {

        private val EXAMPLES = listOf("Player1", "Player2", "@a", "@e", "@r", "@a[gamemode=adventure]")
        private val PLAYER_NAME_REGEX = Regex("[a-zA-Z0-9_]{1,16}")

        // Constants
        private val PLAYER = EntityArgumentType(true, true)
        private val PLAYERS = EntityArgumentType(true, false)
        private val ENTITY = EntityArgumentType(false, true)
        private val ENTITIES = EntityArgumentType(false, false)

        @JvmStatic
        fun from(players: Boolean, singleTarget: Boolean): EntityArgumentType {
            if (players) return if (singleTarget) PLAYER else PLAYERS
            return if (singleTarget) ENTITY else ENTITIES
        }

        /**
         * An argument that will only attempt to parse a single player.
         */
        @JvmStatic
        fun player(): EntityArgumentType = PLAYER

        /**
         * An argument that will attempt to parse multiple players.
         */
        @JvmStatic
        fun players(): EntityArgumentType = PLAYERS

        /**
         * An argument that will attempt to parse a single entity.
         */
        @JvmStatic
        fun entity(): EntityArgumentType = ENTITY

        /**
         * An argument that will attempt to parse multiple entities.
         */
        @JvmStatic
        fun entities(): EntityArgumentType = ENTITIES

        @JvmStatic
        fun getPlayers(context: CommandContext<CommandSourceStack>, name: String): List<AquaPlayer> =
            context.getArgument(name, EntityQuery::class.java).getPlayers(context.source)
    }
}
