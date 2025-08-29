package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.VectorArgument
import net.aquamine.server.command.arguments.entities.EntityArgumentType
import net.aquamine.server.locale.CommandMessages

object TeleportCommand {

    private const val LOCATION = "location"
    private const val PLAYERS = "players"
    private const val TARGET = "target"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        val node = dispatcher.register(literalCommand("teleport") {
            requiresPermission(AquaPermission.TELEPORT)
            argument(LOCATION, VectorArgument.normal()) {
                runs { it.source.getPlayerOrError().teleport(VectorArgument.get(it, LOCATION)) }
            }
            argument(PLAYERS, EntityArgumentType.players()) {
                runs {
                    val player = it.source.getPlayerOrError()
                    val targets = EntityArgumentType.getPlayers(it, PLAYERS)
                    if (targets.size == 1) {
                        val target = targets[0]
                        player.teleport(target.position)
                        CommandMessages.TELEPORT_SINGLE.sendSuccess(it.source, it.source.displayName, target.displayName, true)
                    }
                }
                argument(TARGET, EntityArgumentType.players()) {
                    runs { context ->
                        val players = EntityArgumentType.getPlayers(context, PLAYERS)
                        val target = EntityArgumentType.getPlayers(context, TARGET)[0]
                        players.forEach { it.teleport(target.position) }
                        CommandMessages.TELEPORT_ENTITY_MULTIPLE.sendSuccess(context.source, players.size, target.displayName, true)
                    }
                }
                argument(LOCATION, VectorArgument.normal()) {
                    runs { context ->
                        val players = EntityArgumentType.getPlayers(context, PLAYERS)
                        val location = VectorArgument.get(context, LOCATION)
                        players.forEach { it.teleport(location) }
                        CommandMessages.TELEPORT_LOCATION_MULTIPLE.sendSuccess(context.source, players.size, location, true)
                    }
                }
            }
        })
        dispatcher.register(LiteralArgumentBuilder.literal<CommandSourceStack>("tp").redirect(node))
    }
}
