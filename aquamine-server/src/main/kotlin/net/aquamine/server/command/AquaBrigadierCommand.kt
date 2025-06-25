package net.aquamine.server.command

import com.mojang.brigadier.tree.LiteralCommandNode
import net.aquamine.api.command.BrigadierCommand
import net.aquamine.api.command.CommandExecutionContext

class AquaBrigadierCommand(override val node: LiteralCommandNode<CommandExecutionContext>) : BrigadierCommand {

    object Factory : BrigadierCommand.Factory {

        override fun of(node: LiteralCommandNode<CommandExecutionContext>): AquaBrigadierCommand = AquaBrigadierCommand(node)
    }
}
