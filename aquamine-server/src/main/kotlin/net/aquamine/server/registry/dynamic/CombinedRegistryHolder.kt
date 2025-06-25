package net.aquamine.server.registry.dynamic

import net.aquamine.api.registry.DefaultedRegistry
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.resource.ResourceKey

class CombinedRegistryHolder(private vararg val delegates: RegistryHolder) : RegistryHolder {

    override val registries: Collection<Registry<*>>
        get() = delegates.flatMap { it.registries }

    override fun <E> getRegistry(key: ResourceKey<out Registry<E>>): Registry<E>? {
        for (delegate in delegates) {
            val registry = delegate.getRegistry(key)
            if (registry != null) return registry
        }
        return null
    }

    override fun <E> getDefaultedRegistry(key: ResourceKey<out Registry<E>>): DefaultedRegistry<E>? = getRegistry(key) as? DefaultedRegistry<E>
}
