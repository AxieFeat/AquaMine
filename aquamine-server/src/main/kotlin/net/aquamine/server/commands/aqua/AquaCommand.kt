package net.aquamine.server.commands.aqua

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack

object AquaCommand {

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("aquamine") {
            registerSubCommand(PluginsCommand)
            registerSubCommand(InfoCommand)
        })
    }

    @JvmStatic
    private fun LiteralArgumentBuilder<CommandSourceStack>.registerSubCommand(command: AquaSubCommand) {
        val node = command.register().build()
        this.then(node)
        command.aliases().forEach { this.then(LiteralArgumentBuilder.literal<CommandSourceStack>(it).redirect(node)) }
    }
}
