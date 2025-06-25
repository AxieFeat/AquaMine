package net.aquamine.server.registry.network

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import net.aquamine.api.registry.Registry
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.registry.KryptonRegistry
import net.aquamine.server.registry.KryptonSimpleRegistry
import net.aquamine.server.registry.holder.HolderSet
import net.aquamine.server.resource.KryptonResourceKey
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.MapCodec
import org.kryptonmc.serialization.codecs.RecordCodecBuilder

object RegistryCodecs {

    @JvmStatic
    private fun <T> withNameAndId(key: ResourceKey<out Registry<T>>, elementCodec: MapCodec<T>): MapCodec<RegistryEntry<T>> {
        return RecordCodecBuilder.createMap { instance ->
            instance.group(
                KryptonResourceKey.codec(key).fieldOf("name").getting { it.key },
                Codec.INT.fieldOf("id").getting { it.id },
                elementCodec.getting { it.value }
            ).apply(instance, ::RegistryEntry)
        }
    }

    @JvmStatic
    fun <T> network(key: ResourceKey<out Registry<T>>, elementCodec: Codec<T>): Codec<KryptonRegistry<T>> {
        return withNameAndId(key, elementCodec.fieldOf("element")).codec().listOf().xmap({ entries ->
            val registry = KryptonSimpleRegistry.standard(key)
            entries.forEach { entry -> registry.register(entry.id, entry.key, entry.value) }
            registry
        }, { registry ->
            val result = ImmutableList.builder<RegistryEntry<T>>()
            registry.forEach { value -> result.add(RegistryEntry(registry.getResourceKey(value)!!, registry.getId(value), value)) }
            result.build()
        })
    }

    @JvmStatic
    fun <E> full(key: ResourceKey<out Registry<E>>, elementCodec: Codec<E>): Codec<KryptonRegistry<E>> {
        val elementsCodec = Codec.map(KryptonResourceKey.codec(key), elementCodec)
        return elementsCodec.xmap({ elements ->
            val registry = KryptonSimpleRegistry.standard(key)
            elements.forEach { (key, value) -> registry.register(key, value) }
            registry.freeze()
        }, { ImmutableMap.copyOf(it.entries()) })
    }

    @JvmStatic
    fun <E> homogenousList(key: ResourceKey<out Registry<E>>, elementCodec: Codec<E>): Codec<HolderSet<E>> = homogenousList(key, elementCodec, false)

    @JvmStatic
    fun <E> homogenousList(key: ResourceKey<out Registry<E>>, elementCodec: Codec<E>, noInline: Boolean): Codec<HolderSet<E>> =
        HolderSetCodec.create(key, RegistryFileCodec.create(key, elementCodec), noInline)

    @JvmRecord
    private data class RegistryEntry<T>(val key: ResourceKey<T>, val id: Int, val value: T)
}
