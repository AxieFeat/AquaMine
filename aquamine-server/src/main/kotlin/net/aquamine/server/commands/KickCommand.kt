package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import net.kyori.adventure.text.Component
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.entities.EntityArgumentType
import net.aquamine.server.locale.DisconnectMessages

object KickCommand {

    private const val TARGETS = "targets"
    private const val REASON = "reason"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("kick") {
            requiresPermission(AquaPermission.KICK)
            argument(TARGETS, EntityArgumentType.players()) {
                runs { context -> EntityArgumentType.getPlayers(context, TARGETS).forEach { it.disconnect(DisconnectMessages.KICKED) } }
                argument(REASON, StringArgumentType.string()) {
                    runs { context ->
                        val reason = context.getArgument(REASON, String::class.java)
                        EntityArgumentType.getPlayers(context, TARGETS).forEach {
                            it.disconnect(DisconnectMessages.KICKED.append(Component.text(" Reason: $reason")))
                        }
                    }
                }
            }
        })
    }
}
