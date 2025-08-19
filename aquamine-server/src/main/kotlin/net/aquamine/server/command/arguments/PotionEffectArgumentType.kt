package net.aquamine.server.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.potion.AquaPotionType
import net.aquamine.server.registry.AquaRegistries
import net.kyori.adventure.key.InvalidKeyException
import net.kyori.adventure.key.Key
import java.util.concurrent.CompletableFuture

/**
 * An argument type that parses potion effects.
 */
// TODO REMOVE this class, in minecraft client for potion types used Resource location argument.
object PotionEffectArgumentType : ArgumentType<AquaPotionType> {

    private val EXAMPLES = listOf("minecraft:speed", "haste")
    private val ERROR_UNKNOWN_POTION_TYPE = CommandExceptions.dynamic("potion.notFound") // TODO Correct error message key
    private val ERROR_INVALID = CommandExceptions.simple("argument.id.invalid")

    override fun getExamples(): Collection<String> = EXAMPLES

    @JvmStatic
    fun get(context: CommandContext<CommandSourceStack>, name: String): AquaPotionType = context.getArgument(name, AquaPotionType::class.java)

    override fun parse(reader: StringReader): AquaPotionType {
        val cursor = reader.cursor
        try {
            val key = Key.key(StringReading.readKeyString(reader))
            return AquaRegistries.POTION_TYPE.get(key) ?:
                CommandExceptions.resetAndThrow(reader, cursor, ERROR_UNKNOWN_POTION_TYPE.create(key))
        } catch (_: InvalidKeyException) {
            CommandExceptions.resetAndThrow(reader, cursor, ERROR_INVALID.createWithContext(reader))
        }
    }

    override fun <S : Any?> listSuggestions(
        context: CommandContext<S?>?,
        builder: SuggestionsBuilder?
    ): CompletableFuture<Suggestions?>? {
        AquaRegistries.POTION_TYPE.map { it.key().asString() }.forEach { builder?.suggest(it) }

        return builder?.buildFuture()
    }
}