package net.aquamine.server.tags

import com.google.common.collect.Interners
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registry
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.tags.TagKey
import net.aquamine.server.util.Keys
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.DataResult

@JvmRecord
data class KryptonTagKey<T>(override val registry: ResourceKey<out Registry<T>>, override val location: Key) : TagKey<T> {

    object Factory : TagKey.Factory {

        override fun <T> of(registry: ResourceKey<out Registry<T>>, location: Key): TagKey<T> = KryptonTagKey.of(registry, location)
    }

    companion object {

        private val VALUES = Interners.newWeakInterner<KryptonTagKey<*>>()

        @JvmStatic
        @Suppress("UNCHECKED_CAST")
        fun <T> of(registry: ResourceKey<out Registry<T>>, location: Key): KryptonTagKey<T> =
            VALUES.intern(KryptonTagKey(registry, location)) as KryptonTagKey<T>

        @JvmStatic
        fun <T> hashedCodec(registry: ResourceKey<out Registry<T>>): Codec<TagKey<T>> = Codec.STRING.comapFlatMap(
            { input ->
                if (input.startsWith('#')) {
                    Keys.read(input.substring(1)).map { of(registry, it) }
                } else {
                    DataResult.error("$input is not a valid tag ID!")
                }
            },
            { "#${it.location}" }
        )
    }
}
