package net.aquamine.server.command

import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.aquamine.server.adventure.AquaAdventure
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.util.Keys
import java.util.concurrent.CompletableFuture

/**
 * Holds built-in suggestion providers that are used client-side to make
 * suggestions without bothering the server.
 *
 * All retrievals in here will default to returning information about the
 * special "ask_server" suggestion provider name, which instructs the client
 * that they should bother the server for custom suggestions.
 */
object SuggestionProviders {

    private val PROVIDERS_BY_NAME = HashMap<Key, SuggestionProvider<CommandSourceStack>>()
    private val DEFAULT_NAME = Key.key("ask_server")

    /**
     * Suggestion provider that suggests all entity types that can be summoned
     * through some means, such as with the `/summon` command.
     */
    @JvmField
    val SUMMONABLE_ENTITIES: SuggestionProvider<CommandSourceStack> = register(Key.key("summonable_entities")) { _, builder ->
        val registry = KryptonRegistries.ENTITY_TYPE
        CommandSuggestionProvider.suggestResource(registry.stream().filter { it.isSummonable }, builder, { registry.getKey(it) }) {
            AquaAdventure.asMessage(Component.translatable(Keys.translation("entity", registry.getKey(it))))
        }
    }

    @JvmStatic
    fun getName(provider: SuggestionProvider<CommandSourceStack>): Key = if (provider is Wrapper) provider.name else DEFAULT_NAME

    @JvmStatic
    private fun register(key: Key, provider: SuggestionProvider<CommandSourceStack>): SuggestionProvider<CommandSourceStack> {
        require(!PROVIDERS_BY_NAME.containsKey(key)) { "A command suggestion provider is already registered with the given key $key!" }
        PROVIDERS_BY_NAME.put(key, provider)
        return Wrapper(key, provider)
    }

    @JvmRecord
    private data class Wrapper(
        val name: Key,
        private val delegate: SuggestionProvider<CommandSourceStack>
    ) : SuggestionProvider<CommandSourceStack> {

        override fun getSuggestions(context: CommandContext<CommandSourceStack>?, builder: SuggestionsBuilder?): CompletableFuture<Suggestions> =
            delegate.getSuggestions(context, builder)
    }
}
