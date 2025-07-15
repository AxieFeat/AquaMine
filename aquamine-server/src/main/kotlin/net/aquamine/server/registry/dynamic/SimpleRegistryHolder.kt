package net.aquamine.server.registry.dynamic

import net.aquamine.api.registry.DefaultedRegistry
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.resource.ResourceKey

class SimpleRegistryHolder(private val entries: Map<out ResourceKey<out Registry<*>>, Registry<*>>) : RegistryHolder {

    override val registries: Collection<Registry<*>>
        get() = entries.values

    @Suppress("UNCHECKED_CAST")
    override fun <E> getRegistry(key: ResourceKey<out Registry<E>>): Registry<E>? = entries[key] as? Registry<E>

    override fun <E> getDefaultedRegistry(key: ResourceKey<out Registry<E>>): DefaultedRegistry<E>? = getRegistry(key) as? DefaultedRegistry<E>
}
