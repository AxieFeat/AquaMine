package net.aquamine.server.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.aquamine.api.util.Position
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.CommandSuggestionProvider
import net.aquamine.server.command.arguments.coordinates.Coordinates
import net.aquamine.server.command.arguments.coordinates.LocalCoordinates
import net.aquamine.server.command.arguments.coordinates.TextCoordinates
import net.aquamine.server.command.arguments.coordinates.WorldCoordinates
import java.util.concurrent.CompletableFuture

/**
 * An argument type that will parse all 3 types of coordinates.
 *
 * When [correctCenter] is set to true, 0.5 will be added to the X, Y, and Z
 * values to correct coordinates in to the centre of a block.
 */
class VectorArgument private constructor(private val correctCenter: Boolean = true) : ArgumentType<Coordinates> {

    override fun parse(reader: StringReader): Coordinates {
        if (reader.canRead() && reader.peek() == '^') return LocalCoordinates.parse(reader)
        return WorldCoordinates.parse(reader, correctCenter)
    }

    override fun <S> listSuggestions(context: CommandContext<S>, builder: SuggestionsBuilder): CompletableFuture<Suggestions> {
        if (context.source !is CommandSourceStack) return Suggestions.empty()
        val remaining = builder.remaining
        val suggestion = if (remaining.isNotEmpty() && remaining[0] == '^') TextCoordinates.CENTER_LOCAL else TextCoordinates.CENTER_GLOBAL
        return CommandSuggestionProvider.suggestCoordinates(remaining, suggestion, builder) {
            try {
                parse(StringReader(it))
                true
            } catch (_: CommandSyntaxException) {
                false
            }
        }
    }

    override fun getExamples(): Collection<String> = EXAMPLES

    companion object {

        private val EXAMPLES = listOf("0 0 0", "~ ~ ~", "^ ^ ^", "^1 ^ ^-5", "0.1 -0.5 .9", "~0.5 ~1 ~-5")
        private val NORMAL = VectorArgument(true)

        /**
         * A vector argument that will correct coordinates to the centre of a
         * block by adding 0.5 to them.
         */
        @JvmStatic
        fun normal(): VectorArgument = NORMAL

        @JvmStatic
        fun get(context: CommandContext<CommandSourceStack>, name: String): Position =
            context.getArgument(name, Coordinates::class.java).calculatePosition(context.source)
    }
}
