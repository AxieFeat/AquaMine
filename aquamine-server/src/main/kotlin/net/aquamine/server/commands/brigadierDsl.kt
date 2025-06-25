package net.aquamine.server.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import net.aquamine.annotations.dsl.BrigadierDsl
import net.aquamine.server.command.CommandSourceStack

@BrigadierDsl
inline fun <T : Builder<T>> Builder<T>.runs(crossinline action: (CommandContext<CommandSourceStack>) -> Unit): Builder<T> = executes {
    action(it)
    Command.SINGLE_SUCCESS
}

@BrigadierDsl
fun LiteralArgumentBuilder<CommandSourceStack>.requiresPermission(permission: KryptonPermission): LiteralArgumentBuilder<CommandSourceStack> =
    requires { it.hasPermission(permission) }

/**
 * Equivalent to [CommandContext.getArgument], except this uses a reified type
 * to improve QOL in Kotlin. This also doesn't return a platform type.
 */
inline fun <reified T> CommandContext<*>.getArgument(name: String): T = getArgument(name, T::class.java)

private typealias Builder<T> = ArgumentBuilder<CommandSourceStack, T>
