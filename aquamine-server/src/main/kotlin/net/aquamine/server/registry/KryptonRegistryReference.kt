package net.aquamine.server.registry

import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryReference
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.resource.KryptonResourceKey

class KryptonRegistryReference<T, V : T>(private val registry: Registry<T>, override val key: ResourceKey<V>) : RegistryReference<V> {

    @Suppress("UNCHECKED_CAST")
    override fun get(): V = registry.get(key as ResourceKey<T>) as V

    object Factory : RegistryReference.Factory {

        override fun <T, V : T> of(registry: Registry<T>, key: Key): RegistryReference<V> {
            return KryptonRegistryReference(registry, KryptonResourceKey.of(registry.key.location, key))
        }
    }
}
