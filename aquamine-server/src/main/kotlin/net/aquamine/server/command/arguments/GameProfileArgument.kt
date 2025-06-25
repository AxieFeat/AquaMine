package net.aquamine.server.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.command.CommandExecutionContext
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.entities.EntityArgumentParser
import net.aquamine.server.command.arguments.entities.EntityQuery

/**
 * Parses game profiles as entity selectors for single player targets.
 */
object GameProfileArgument : ArgumentType<EntityQuery> {

    override fun parse(reader: StringReader): EntityQuery {
        if (reader.canRead() && reader.peek() == EntityArgumentParser.SELECTOR_CHAR) {
            reader.skip()
            return EntityArgumentParser.parse(reader, true, false)
        }
        return EntityQuery(EntityQuery.Selector.PLAYER, StringReading.readNonSpaceString(reader))
    }

    @JvmStatic
    fun <S : CommandExecutionContext> get(context: CommandContext<S>, name: String): List<GameProfile> =
        context.getArgument(name, EntityQuery::class.java).getProfiles(context.source as CommandSourceStack)
}
