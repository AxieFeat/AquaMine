package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.api.world.GameMode
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.GameModeArgument
import net.aquamine.server.command.arguments.entities.EntityArgumentType
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.locale.CommandMessages
import net.aquamine.server.world.rule.GameRuleKeys

object GameModeCommand {

    private const val GAME_MODE = "gamemode"
    private const val TARGETS = "targets"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("gamemode") {
            requiresPermission(AquaPermission.GAME_MODE)

            argument(GAME_MODE, GameModeArgument) {
                runs { setGameMode(it.source, listOf(it.source.getPlayerOrError()), GameModeArgument.get(it, GAME_MODE)) }
                argument(TARGETS, EntityArgumentType.players()) {
                    runs {
                        setGameMode(it.source,
                            EntityArgumentType.getPlayers(it, TARGETS),
                            GameModeArgument.get(it, GAME_MODE)
                        )
                    }
                }
            }
        })
    }

    @JvmStatic
    private fun setGameMode(source: CommandSourceStack, targets: List<AquaPlayer>, mode: GameMode): Int {
        targets.forEach {
            it.updateGameMode(mode)
            notifyGameModeChanged(source, it, mode)
        }
        return targets.size
    }

    @JvmStatic
    private fun notifyGameModeChanged(source: CommandSourceStack, player: AquaPlayer, mode: GameMode) {
        if (source.entity === player) {
            CommandMessages.GAME_MODE_SELF.sendSuccess(source, mode, true)
            return
        }

        if (source.world.gameRules().getBoolean(GameRuleKeys.SEND_COMMAND_FEEDBACK)) {
            player.sendSystemMessage(CommandMessages.GAME_MODE_CHANGED.build(mode))
        }

        CommandMessages.GAME_MODE_OTHER.sendSuccess(source, player.displayName, mode, true)
    }
}
