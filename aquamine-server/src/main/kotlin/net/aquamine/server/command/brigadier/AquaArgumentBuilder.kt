package net.aquamine.server.command.brigadier

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.tree.CommandNode

class AquaArgumentBuilder<S, T> private constructor(
    private val name: String,
    private val type: ArgumentType<T>
) : ArgumentBuilder<S, AquaArgumentBuilder<S, T>>() {

    private var suggestionsProvider: SuggestionProvider<S>? = null

    fun suggests(provider: SuggestionProvider<S>?): AquaArgumentBuilder<S, T> = apply { suggestionsProvider = provider }

    override fun then(argument: ArgumentBuilder<S, *>?): AquaArgumentBuilder<S, T> {
        throw UnsupportedOperationException("Cannot add children to a greedy node!")
    }

    override fun then(argument: CommandNode<S>?): AquaArgumentBuilder<S, T> {
        throw UnsupportedOperationException("Cannot add children to a greedy node!")
    }

    override fun getThis(): AquaArgumentBuilder<S, T> = this

    override fun build(): AquaArgumentCommandNode<S, T> =
        AquaArgumentCommandNode(name, type, command, requirement, contextRequirement, redirect, redirectModifier, isFork, suggestionsProvider)

    companion object {

        @JvmStatic
        fun <S, T> AquaArgument(name: String, type: ArgumentType<T>): AquaArgumentBuilder<S, T> = AquaArgumentBuilder(name, type)
    }
}
