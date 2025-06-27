package net.aquamine.server.registry.dynamic

import com.google.common.collect.ImmutableMap
import net.aquamine.api.registry.Registry
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.registry.AquaRegistry
import net.aquamine.server.registry.holder.HolderLookup
import net.aquamine.server.util.ImmutableMaps
import java.util.stream.Collectors
import java.util.stream.Stream

interface RegistryAccess : HolderLookup.Provider {

    fun <E> getRegistry(key: ResourceKey<out Registry<out E>>): AquaRegistry<E>?

    override fun <T> lookup(key: ResourceKey<out Registry<out T>>): HolderLookup.ForRegistry<T>? = getRegistry(key)?.asLookup()

    fun <E> registryOrThrow(key: ResourceKey<out Registry<out E>>): AquaRegistry<E> = getRegistry(key) ?: error("Missing required registry $key!")

    fun registries(): Stream<RegistryEntry<*>>

    fun freeze(): Frozen {
        class FrozenAccess(entries: Stream<RegistryEntry<*>>) : ImmutableImpl(entries), Frozen
        return FrozenAccess(registries().map { it.freeze() })
    }

    interface Frozen : RegistryAccess

    open class ImmutableImpl : RegistryAccess {

        private val registries: Map<out RegistryKey<*>, AquaRegistry<*>>

        constructor(map: Map<out RegistryKey<*>, AquaRegistry<*>>) {
            registries = ImmutableMaps.copyOf(map)
        }

        constructor(registries: List<AquaRegistry<*>>) {
            this.registries = registries.stream().collect(Collectors.toUnmodifiableMap({ it.key }, { it }))
        }

        constructor(entries: Stream<RegistryEntry<*>>) {
            registries = entries.collect(ImmutableMap.toImmutableMap({ it.key }, { it.value }))
        }

        @Suppress("UNCHECKED_CAST")
        override fun <E> getRegistry(key: RegistryKey<out E>): AquaRegistry<E>? = registries.get(key) as? AquaRegistry<E>

        override fun registries(): Stream<RegistryEntry<*>> = registries.entries.stream().map { RegistryEntry.fromMapEntry(it) }
    }

    @JvmRecord
    data class RegistryEntry<T>(val key: ResourceKey<out Registry<T>>, val value: AquaRegistry<T>) {

        fun freeze(): RegistryEntry<T> = RegistryEntry(key, value.freeze())

        companion object {

            @JvmStatic
            @Suppress("UNCHECKED_CAST")
            fun <T, R : AquaRegistry<out T>> fromMapEntry(entry: Map.Entry<RegistryKey<*>, R>): RegistryEntry<T> =
                RegistryEntry(entry.key as RegistryKey<T>, entry.value as AquaRegistry<T>)
        }
    }

    companion object {

        @JvmField
        val EMPTY: Frozen = ImmutableImpl(ImmutableMaps.of()).freeze()

        @JvmStatic
        fun fromRegistryOfRegistries(registry: AquaRegistry<out AquaRegistry<*>>): Frozen = object : Frozen {
            @Suppress("UNCHECKED_CAST")
            override fun <E> getRegistry(key: ResourceKey<out Registry<out E>>): AquaRegistry<E>? {
                val temp = registry as AquaRegistry<AquaRegistry<E>>
                return temp.get(key as ResourceKey<AquaRegistry<E>>)
            }

            override fun registries(): Stream<RegistryEntry<*>> = registry.entries().stream().map { RegistryEntry.fromMapEntry(it) }

            override fun freeze(): Frozen = this
        }
    }
}

private typealias RegistryKey<T> = ResourceKey<out Registry<T>>
