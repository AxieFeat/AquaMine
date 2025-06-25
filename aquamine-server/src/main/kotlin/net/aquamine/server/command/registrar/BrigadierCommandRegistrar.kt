package net.aquamine.server.command.registrar

import com.mojang.brigadier.tree.LiteralCommandNode
import com.mojang.brigadier.tree.RootCommandNode
import net.aquamine.api.command.BrigadierCommand
import net.aquamine.api.command.CommandMeta
import net.aquamine.server.command.CommandSourceStack
import java.util.concurrent.locks.Lock

/**
 * Registers Brigadier commands to a root node. Brigadier commands are really
 * easy to register, as they are already backed by nodes that we are able to
 * add to the tree.
 */
class BrigadierCommandRegistrar(lock: Lock) : AbstractCommandRegistrar<BrigadierCommand>(lock) {

    @Suppress("UNCHECKED_CAST")
    override fun register(root: RootCommandNode<CommandSourceStack>, command: BrigadierCommand, meta: CommandMeta) {
        val literal = command.node
        val name = literal.name
        if (name == name.lowercase()) register(root, literal as LiteralCommandNode<CommandSourceStack>)
        meta.aliases.forEach { if (name != it) register(root, literal as LiteralCommandNode<CommandSourceStack>, it) }
    }
}
