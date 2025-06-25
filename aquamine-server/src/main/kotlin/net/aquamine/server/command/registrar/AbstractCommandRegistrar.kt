package net.aquamine.server.command.registrar

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.tree.LiteralCommandNode
import com.mojang.brigadier.tree.RootCommandNode
import net.aquamine.api.command.Command
import net.aquamine.api.command.CommandMeta
import net.aquamine.server.command.CommandSourceStack
import java.util.concurrent.locks.Lock
import kotlin.concurrent.withLock

abstract class AbstractCommandRegistrar<C : Command>(private val lock: Lock) {

    abstract fun register(root: RootCommandNode<CommandSourceStack>, command: C, meta: CommandMeta)

    protected fun register(root: RootCommandNode<CommandSourceStack>, node: LiteralCommandNode<CommandSourceStack>) {
        lock.withLock {
            root.removeChildByName(node.name)
            root.addChild(node)
        }
    }

    protected fun register(root: RootCommandNode<CommandSourceStack>, node: LiteralCommandNode<CommandSourceStack>, alias: String) {
        register(root, copyRedirect(node, alias))
    }

    companion object {

        @JvmStatic
        private fun copyRedirect(source: LiteralCommandNode<CommandSourceStack>, redirectAlias: String): LiteralCommandNode<CommandSourceStack> {
            val builder = LiteralArgumentBuilder.literal<CommandSourceStack>(redirectAlias)
                .requires(source.requirement)
                .requiresWithContext(source.contextRequirement)
                .forward(source.redirect, source.redirectModifier, source.isFork)
                .executes(source.command)
            source.children.forEach { builder.then(it) }
            return builder.build()
        }
    }
}
