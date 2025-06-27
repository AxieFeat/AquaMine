package net.aquamine.server.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import net.kyori.adventure.key.InvalidKeyException
import net.kyori.adventure.key.Key
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.registry.AquaRegistries

/**
 * An argument type that parses entity types that are summonable, in the form
 * of namespaced keys.
 *
 * The default namespace accepted here is "minecraft", just like with
 * everywhere else that keys are used, and that is also valid for entity type
 * keys here, so "pig" and "minecraft:pig" are equivalent.
 */
object SummonEntityArgument : ArgumentType<Key> {

    private val EXAMPLES = setOf("minecraft:pig", "cow")
    private val ERROR_UNKNOWN_ENTITY = CommandExceptions.dynamic("entity.notFound")
    private val ERROR_INVALID = CommandExceptions.simple("argument.id.invalid")

    /**
     * Ensures the given [key] is both a valid entity type **and** the entity
     * type that mapped to the key is summonable.
     *
     * If the entity type is not summonable, [ERROR_UNKNOWN_ENTITY] will be
     * thrown, despite the entity actually being known.
     */
    @JvmStatic
    fun ensureSummonable(key: Key): Key {
        val type = AquaRegistries.ENTITY_TYPE.get(key)
        if (!type.isSummonable) throw ERROR_UNKNOWN_ENTITY.create(key)
        return type.key()
    }

    @JvmStatic
    fun get(context: CommandContext<CommandSourceStack>, name: String): Key = ensureSummonable(context.getArgument(name, Key::class.java))

    override fun parse(reader: StringReader): Key = ensureSummonable(readKey(reader))

    override fun getExamples(): Collection<String> = EXAMPLES

    @JvmStatic
    private fun readKey(reader: StringReader): Key {
        val cursor = reader.cursor
        try {
            return Key.key(StringReading.readKeyString(reader))
        } catch (_: InvalidKeyException) {
            CommandExceptions.resetAndThrow(reader, cursor, ERROR_INVALID.createWithContext(reader))
        }
    }
}
