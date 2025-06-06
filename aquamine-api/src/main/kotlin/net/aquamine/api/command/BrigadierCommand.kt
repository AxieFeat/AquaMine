@file:JvmSynthetic
package net.aquamine.api.command

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.tree.LiteralCommandNode
import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine

/**
 * A command that is backed by a Brigadier [LiteralCommandNode].
 */
@ImmutableType
interface BrigadierCommand : Command {

    /**
     * The built command node representing the command tree for this specific
     * command.
     */
    val node: LiteralCommandNode<CommandExecutionContext>

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(node: LiteralCommandNode<CommandExecutionContext>): BrigadierCommand
    }

    companion object {

        /**
         * Creates a new command backed by the given Brigadier command [node].
         *
         * @param node The backing command node.
         *
         * @return A new Brigadier command.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun of(node: LiteralCommandNode<CommandExecutionContext>): BrigadierCommand = AquaMine.factory<Factory>().of(node)

        /**
         * Creates a new command backed by the Brigadier command node built
         * from the given [builder].
         *
         * @param builder The builder to build the backing command node from.
         *
         * @return A new Brigadier command.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun of(builder: LiteralArgumentBuilder<CommandExecutionContext>): BrigadierCommand = of(builder.build())
    }
}
