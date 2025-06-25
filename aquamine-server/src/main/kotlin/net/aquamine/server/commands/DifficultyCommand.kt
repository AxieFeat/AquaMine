package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import net.aquamine.api.command.literalCommand
import net.aquamine.api.world.Difficulty
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.CommandExceptions
import net.aquamine.server.locale.CommandMessages

object DifficultyCommand {

    private val ERROR_ALREADY_DIFFICULT = CommandExceptions.dynamic("commands.difficulty.failure")

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        val command = literalCommand<CommandSourceStack>("difficulty") {
            requiresPermission(KryptonPermission.DIFFICULTY)
            runs { CommandMessages.DIFFICULTY_QUERY.sendSuccess(it.source, it.source.world.difficulty, false) }
        }
        Difficulty.values().forEach { difficulty ->
            command.then(literalCommand(difficulty.name.lowercase()) {
                runs {
                    if (it.source.world.difficulty == difficulty) throw ERROR_ALREADY_DIFFICULT.create(difficulty.name.lowercase())
                    it.source.world.difficulty = difficulty
                    CommandMessages.DIFFICULTY_SUCCESS.sendSuccess(it.source, difficulty, true)
                }
            })
        }
        dispatcher.register(command)
    }
}
