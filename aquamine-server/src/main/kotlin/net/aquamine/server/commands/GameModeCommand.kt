package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import net.kyori.adventure.text.Component
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.api.world.GameMode
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.GameModeArgument
import net.aquamine.server.command.arguments.entities.EntityArgumentType
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.locale.CommandMessages
import net.aquamine.server.util.enumhelper.GameModes
import net.aquamine.server.world.rule.GameRuleKeys

object GameModeCommand {

    private const val GAME_MODE = "gamemode"
    private const val TARGETS = "targets"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        val command = literalCommand("gamemode") { requiresPermission(AquaPermission.GAME_MODE) }
        GameModes.VALUES.forEach { mode ->
            command.then(literalCommand(mode.name.lowercase()) {
                runs { setMode(it.source, listOf(it.source.getPlayerOrError()), mode) }
                argument(TARGETS, EntityArgumentType.players()) {
                    runs { setMode(it.source, EntityArgumentType.getPlayers(it, TARGETS), mode) }
                }
            })
        }

        command.argument(GAME_MODE, GameModeArgument) {
            runs { setMode(it.source, listOf(it.source.getPlayerOrError()), GameModeArgument.get(it, GAME_MODE)) }
            argument(TARGETS, EntityArgumentType.players()) {
                runs {
                    setMode(it.source,
                        EntityArgumentType.getPlayers(it, TARGETS),
                        GameModeArgument.get(it, GAME_MODE)
                    )
                }
            }
        }
        dispatcher.register(command)
    }

    @JvmStatic
    private fun setMode(source: CommandSourceStack, targets: List<AquaPlayer>, mode: GameMode): Int {
        var count = 0
        targets.forEach {
            it.updateGameMode(mode)
            notifyGameModeChanged(source, it, mode)
            ++count
        }
        return count
    }

    @JvmStatic
    private fun notifyGameModeChanged(source: CommandSourceStack, player: AquaPlayer, mode: GameMode) {
        if (source.entity === player) {
            CommandMessages.GAME_MODE_SELF.sendSuccess(source, mode, true)
            return
        }
        if (source.world.gameRules().getBoolean(GameRuleKeys.SEND_COMMAND_FEEDBACK)) {
            source.sendSystemMessage(Component.translatable("gameMode.changed", Component.translatable(mode)))
        }
        CommandMessages.GAME_MODE_OTHER.sendSuccess(source, player.displayName, mode, true)
    }
}
