package net.aquamine.server.tags

import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registry
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.pack.resources.ResourceManager
import net.aquamine.server.pack.resources.reload.PreparableReloadListener
import net.aquamine.server.registry.dynamic.RegistryAccess
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.resource.AquaResourceKey
import net.aquamine.server.resource.AquaResourceKeys
import net.aquamine.server.util.ImmutableLists
import net.aquamine.server.util.ImmutableMaps
import net.aquamine.server.util.NoSpread
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.stream.Collectors

class TagManager(private val registryAccess: RegistryAccess) : PreparableReloadListener {

    private var results: List<LoadResult<*>> = ImmutableLists.of()

    fun result(): List<LoadResult<*>> = results

    override fun reload(barrier: PreparableReloadListener.PreparationBarrier, manager: ResourceManager, backgroundExecutor: Executor,
                        mainExecutor: Executor): CompletableFuture<Void> {
        val resultFutures = registryAccess.registries().map { createLoader(manager, backgroundExecutor, it) }.toList()
        return NoSpread.completableFutureAllOf(resultFutures.toTypedArray())
            .thenCompose { barrier.wait(it) }
            .thenAcceptAsync({ results = resultFutures.stream().map { it.join() }.collect(Collectors.toUnmodifiableList()) }, mainExecutor)
    }

    private fun <T> createLoader(resourceManager: ResourceManager, backgroundExecutor: Executor,
                                 registryEntry: RegistryAccess.RegistryEntry<T>): CompletableFuture<LoadResult<T>> {
        val (key, registry) = registryEntry
        val loader = TagLoader<Holder<T>>({ registry.getHolder(AquaResourceKey.of(key, it)) }, getTagDirectory(key))
        return CompletableFuture.supplyAsync({ LoadResult(key, loader.loadAndBuild(resourceManager)) }, backgroundExecutor)
    }

    @JvmRecord
    data class LoadResult<T>(val key: ResourceKey<out Registry<T>>, val tags: Map<Key, Collection<Holder<T>>>)

    companion object {

        private val CUSTOM_REGISTRY_DIRECTORIES = ImmutableMaps.of(
            AquaResourceKeys.BLOCK, "tags/blocks",
            AquaResourceKeys.ENTITY_TYPE, "tags/entity_types",
            AquaResourceKeys.FLUID, "tags/fluids",
            AquaResourceKeys.GAME_EVENT, "tags/game_events",
            AquaResourceKeys.ITEM, "tags/items"
        )

        @JvmStatic
        fun getTagDirectory(key: ResourceKey<out Registry<*>>): String = CUSTOM_REGISTRY_DIRECTORIES.get(key) ?: "tags/${key.location.value()}"
    }
}
