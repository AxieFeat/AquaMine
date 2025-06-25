package net.aquamine.server.registry.network

import com.google.common.collect.ImmutableMap
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.registry.RegistryRoots
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.server.network.chat.RichChatType
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.registry.KryptonRegistry
import net.aquamine.server.registry.dynamic.CombinedRegistryHolder
import net.aquamine.server.registry.dynamic.FilteredRegistryHolder
import net.aquamine.server.registry.dynamic.SimpleRegistryHolder
import net.aquamine.server.resource.KryptonResourceKey
import net.aquamine.server.resource.KryptonResourceKeys
import net.aquamine.server.util.Keys
import net.aquamine.server.util.resultOrError
import net.aquamine.server.world.biome.KryptonBiome
import net.aquamine.server.world.dimension.KryptonDimensionType
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.DataResult
import org.kryptonmc.serialization.codecs.UnboundedMapCodec

object RegistrySerialization {

    private val NETWORKABLE_REGISTRIES = ImmutableMap.builder<ResourceKey<out Registry<*>>, NetworkedRegistryData<*>>().apply {
        put(this, ResourceKeys.BIOME, KryptonBiome.NETWORK_CODEC)
        put(this, KryptonResourceKeys.CHAT_TYPE, RichChatType.CODEC)
        put(this, KryptonResourceKeys.DIMENSION_TYPE, KryptonDimensionType.DIRECT_CODEC)
    }.build()
    @JvmField
    val NETWORK_CODEC: Codec<RegistryHolder> = createNetworkCodec<Any>()

    @JvmStatic
    private fun <E> put(map: ImmutableMap.Builder<ResourceKey<out Registry<*>>, NetworkedRegistryData<*>>, key: ResourceKey<out Registry<E>>,
                        networkCodec: Codec<E>) {
        map.put(key, NetworkedRegistryData(key, networkCodec))
    }

    @JvmStatic
    private fun ownedNetworkableRegistries(holder: RegistryHolder): RegistryHolder {
        return FilteredRegistryHolder(holder) { NETWORKABLE_REGISTRIES.containsKey(it) }
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    private fun <E> getNetworkCodec(key: ResourceKey<out Registry<E>>): DataResult<out Codec<E>> {
        return NETWORKABLE_REGISTRIES.get(key)?.networkCodec
            .resultOrError { "Unknown or not serializable registry $key!" } as DataResult<out Codec<E>>
    }

    @JvmStatic
    private fun <E> createNetworkCodec(): Codec<RegistryHolder> {
        val keyCodec: Codec<ResourceKey<out Registry<E>>> = Keys.CODEC.xmap({ KryptonResourceKey.of(RegistryRoots.MINECRAFT, it) }, { it.location })
        val registryCodec: Codec<KryptonRegistry<E>> = keyCodec.partialDispatch(
            "type",
            { DataResult.success(it.key) },
            { key -> getNetworkCodec(key).map { RegistryCodecs.network(key, it) } }
        )
        val mapCodec = Codec.map(keyCodec, registryCodec) as UnboundedMapCodec<out ResourceKey<out Registry<*>>, out KryptonRegistry<*>>
        return captureMap(mapCodec)
    }

    @JvmStatic
    private fun <K : ResourceKey<out Registry<*>>, V : KryptonRegistry<*>> captureMap(codec: UnboundedMapCodec<K, V>): Codec<RegistryHolder> {
        return codec.xmap(
            { registries -> SimpleRegistryHolder(registries) },
            { holder ->
                @Suppress("UNCHECKED_CAST")
                holder.registries.associate { it.key as K to it as V }
            }
        )
    }

    @JvmStatic
    fun networkedRegistries(holder: RegistryHolder): RegistryHolder = ownedNetworkableRegistries(holder)

    @JvmStatic
    fun networkSafeRegistries(dynamic: RegistryHolder): RegistryHolder {
        val statics = KryptonRegistries.StaticHolder
        val networked = networkedRegistries(dynamic)
        return CombinedRegistryHolder(statics, networked)
    }

    @JvmRecord
    private data class NetworkedRegistryData<E>(val key: ResourceKey<out Registry<E>>, val networkCodec: Codec<E>)
}
