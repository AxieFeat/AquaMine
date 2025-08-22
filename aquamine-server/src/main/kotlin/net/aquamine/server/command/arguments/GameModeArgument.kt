package net.aquamine.server.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import net.aquamine.api.world.GameMode
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.util.enumhelper.GameModes

object GameModeArgument : ArgumentType<GameMode> {

    private val EXAMPLES = setOf("survival", "creative", "adventure")
    private val ERROR_INVALID = CommandExceptions.dynamic("argument.gamemode.invalid")

    override fun parse(reader: StringReader): GameMode {
        val name = reader.readUnquotedString()

        return GameModes.fromName(name) ?: throw ERROR_INVALID.create(name)
    }

    override fun getExamples(): Collection<String> = EXAMPLES

    @JvmStatic
    fun get(context: CommandContext<CommandSourceStack>, name: String): GameMode = context.getArgument(name, GameMode::class.java)
}
