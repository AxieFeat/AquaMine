package net.aquamine.server.network.chat

import com.mojang.brigadier.ParseResults
import com.mojang.brigadier.context.CommandContextBuilder
import com.mojang.brigadier.tree.ArgumentCommandNode
import net.aquamine.server.command.argument.SignedArgument

@JvmRecord
data class SignableCommand<S>(val arguments: List<Argument<S>>) {

    @JvmRecord
    data class Argument<S>(val node: ArgumentCommandNode<S, *>, val value: String) {

        fun name(): String = node.name
    }

    companion object {

        @JvmStatic
        fun <S> of(results: ParseResults<S>): SignableCommand<S> {
            val text = results.reader.string
            val context = results.context
            var currentContext: CommandContextBuilder<S> = context

            val arguments = collectArguments(text, context)
            var childContext: CommandContextBuilder<S>?
            while (currentContext.child.also { childContext = it } != null) {
                val differentRoots = childContext!!.rootNode != context.rootNode
                if (!differentRoots) break
                arguments.addAll(collectArguments(text, childContext))
                currentContext = childContext
            }
            return SignableCommand(arguments)
        }

        @JvmStatic
        private fun <S> collectArguments(text: String, context: CommandContextBuilder<S>): MutableList<Argument<S>> {
            val result = ArrayList<Argument<S>>()
            context.nodes.forEach {
                val node = it.node
                if (node !is ArgumentCommandNode<S, *> || node.type !is SignedArgument<*>) return@forEach
                val argument = context.arguments[node.name]
                if (argument != null) result.add(Argument(node, argument.range.get(text)))
            }
            return result
        }
    }
}
