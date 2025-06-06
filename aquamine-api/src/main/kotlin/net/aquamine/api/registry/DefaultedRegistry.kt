package net.aquamine.api.registry

import net.kyori.adventure.key.Key
import net.aquamine.api.resource.ResourceKey

/**
 * A registry with a default key-value pair.
 */
interface DefaultedRegistry<T> : Registry<T> {

    /**
     * The default key for this defaulted registry.
     */
    val defaultKey: Key

    override fun get(key: Key): T

    override fun get(key: ResourceKey<T>): T

    override fun getKey(value: T): Key
}
