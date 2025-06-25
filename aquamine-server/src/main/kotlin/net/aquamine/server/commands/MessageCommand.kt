package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.kyori.adventure.text.Component
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.entities.EntityArgumentType

object MessageCommand {

    private const val PLAYER = "player"
    private const val MESSAGE = "message"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        val messageCommand = dispatcher.register(literalCommand("msg") {
            requiresPermission(KryptonPermission.MESSAGE)
            argument(PLAYER, EntityArgumentType.players()) {
                argument(MESSAGE, StringArgumentType.string()) {
                    runs {
                        // TODO: Update when new chat changes are implemented
                        val player = EntityArgumentType.getPlayers(it, PLAYER).get(0)
                        val message = Component.text(it.getArgument(MESSAGE, String::class.java))
                        it.source.sendSystemMessage(Component.translatable("commands.message.display.outgoing", player.displayName, message))
                        player.sendSystemMessage(Component.translatable("commands.message.display.incoming", it.source.displayName, message))
                    }
                }
            }
        })

        dispatcher.register(LiteralArgumentBuilder.literal<CommandSourceStack>("tell").redirect(messageCommand))
        dispatcher.register(LiteralArgumentBuilder.literal<CommandSourceStack>("w").redirect(messageCommand))
    }
}
