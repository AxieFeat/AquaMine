package net.aquamine.server.registry.dynamic

import net.aquamine.api.registry.DefaultedRegistry
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.registry.KryptonRegistry
import java.util.Collections

class ImmutableRegistryHolder(private val entries: Map<out ResourceKey<out Registry<*>>, KryptonRegistry<*>>) : RegistryHolder {

    override val registries: Collection<Registry<*>>
        get() = Collections.unmodifiableCollection(entries.values)

    @Suppress("UNCHECKED_CAST")
    override fun <E> getRegistry(key: ResourceKey<out Registry<E>>): Registry<E>? = entries.get(key) as? Registry<E>

    override fun <E> getDefaultedRegistry(key: ResourceKey<out Registry<E>>): DefaultedRegistry<E>? = getRegistry(key) as? DefaultedRegistry<E>
}
