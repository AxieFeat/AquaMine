package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import net.kyori.adventure.text.Component
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack

object SayCommand {

    private const val MESSAGE = "message"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("say") {
            requiresPermission(KryptonPermission.SAY)
            argument(MESSAGE, StringArgumentType.string()) {
                runs {
                    // TODO: Update when new chat changes are implemented
                    val messageText = Component.text(it.getArgument(MESSAGE, String::class.java))
                    it.source.server.sendMessage(Component.translatable("chat.type.announcement", it.source.displayName, messageText))
                }
            }
        })
    }
}
