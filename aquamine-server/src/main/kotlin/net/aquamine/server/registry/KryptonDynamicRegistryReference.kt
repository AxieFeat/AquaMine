package net.aquamine.server.registry

import net.kyori.adventure.key.Key
import net.aquamine.api.registry.DynamicRegistryReference
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.resource.KryptonResourceKey

class KryptonDynamicRegistryReference<T>(
    private val registry: ResourceKey<out Registry<T>>,
    override val key: ResourceKey<T>
) : DynamicRegistryReference<T> {

    override fun get(holder: RegistryHolder): T {
        return requireNotNull(holder.getRegistry(registry)?.get(key)) { "Could not find value for key $key in holder $holder!" }
    }

    object Factory : DynamicRegistryReference.Factory {

        override fun <T> of(registry: ResourceKey<out Registry<T>>, key: Key): DynamicRegistryReference<T> {
            return KryptonDynamicRegistryReference(registry, KryptonResourceKey.of(registry, key))
        }
    }
}
