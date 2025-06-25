package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import net.kyori.adventure.text.Component
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack

object MeCommand {

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("me") {
            requiresPermission(KryptonPermission.ME)
            argument("action", StringArgumentType.string()) {
                runs {
                    // TODO: Update when new chat changes are implemented
                    val actionText = Component.text(it.getArgument("action", String::class.java))
                    it.source.server.sendMessage(Component.translatable("chat.type.emote", it.source.displayName, actionText))
                }
            }
        })
    }
}
