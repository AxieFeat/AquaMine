package net.aquamine.api.registry

import net.aquamine.api.resource.ResourceKey

/**
 * Something that holds registries.
 */
interface RegistryHolder {

    /**
     * All the registries contained within this registry holder.
     */
    val registries: Collection<Registry<*>>

    /**
     * Gets a registry from this registry holder with the given [key].
     *
     * @param E The registry type.
     * @param key The registry key.
     *
     * @return The registry, if present.
     */
    fun <E> getRegistry(key: ResourceKey<out Registry<E>>): Registry<E>?

    /**
     * Gets a defaulted registry from this registry holder with the
     * given [key].
     *
     * @param E The registry type.
     * @param key The registry key.
     *
     * @return The defaulted registry, if present.
     */
    fun <E> getDefaultedRegistry(key: ResourceKey<out Registry<E>>): DefaultedRegistry<E>?
}
