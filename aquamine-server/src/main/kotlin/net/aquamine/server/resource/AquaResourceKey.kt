package net.aquamine.server.resource

import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registry
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.util.Keys
import org.kryptonmc.serialization.Codec
import java.util.Collections
import java.util.IdentityHashMap

class AquaResourceKey<T> private constructor(override val registry: Key, override val location: Key) : ResourceKey<T> {

    override fun toString(): String = "ResourceKey[$registry / $location]"

    object Factory : ResourceKey.Factory {

        override fun <T> of(registry: Key, location: Key): ResourceKey<T> = AquaResourceKey.of(registry, location)
    }

    companion object {

        private val VALUES = Collections.synchronizedMap(IdentityHashMap<String, ResourceKey<*>>())

        @JvmStatic
        @Suppress("UNCHECKED_CAST")
        fun <T> of(registry: Key, location: Key): ResourceKey<T> {
            val key = "$registry:$location".intern()
            return VALUES.computeIfAbsent(key) { AquaResourceKey<T>(registry, location) } as ResourceKey<T>
        }

        @JvmStatic
        fun <T> of(parent: ResourceKey<out Registry<T>>, location: Key): ResourceKey<T> = of(parent.location, location)

        @JvmStatic
        fun <T> codec(key: ResourceKey<out Registry<T>>): Codec<ResourceKey<T>> = Keys.CODEC.xmap({ of(key, it) }, { it.location })
    }
}
