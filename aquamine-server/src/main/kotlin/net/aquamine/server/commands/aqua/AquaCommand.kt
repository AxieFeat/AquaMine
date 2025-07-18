package net.aquamine.server.commands.aqua

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack

object AquaCommand {

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("aquamine") {
            registerSubCommand(this, PluginsCommand)
            registerSubCommand(this, InfoCommand)
            registerSubCommand(this, StatusCommand)
        })
    }

    @JvmStatic
    private fun registerSubCommand(context: LiteralArgumentBuilder<CommandSourceStack>, command: AquaSubCommand) {
        val node = command.register().build()
        context.then(node)
        command.aliases().forEach { context.then(LiteralArgumentBuilder.literal<CommandSourceStack>(it).redirect(node)) }
    }
}
