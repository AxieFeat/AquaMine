package net.aquamine.server.registry.network

import com.google.common.collect.ImmutableMap
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.registry.RegistryRoots
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.server.network.chat.RichChatType
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.registry.AquaRegistry
import net.aquamine.server.registry.dynamic.CombinedRegistryHolder
import net.aquamine.server.registry.dynamic.FilteredRegistryHolder
import net.aquamine.server.registry.dynamic.SimpleRegistryHolder
import net.aquamine.server.resource.AquaResourceKey
import net.aquamine.server.resource.AquaResourceKeys
import net.aquamine.server.util.Keys
import net.aquamine.server.util.resultOrError
import net.aquamine.server.world.biome.AquaBiome
import net.aquamine.server.world.dimension.AquaDimensionType
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.DataResult
import org.kryptonmc.serialization.codecs.UnboundedMapCodec

object RegistrySerialization {

    private val NETWORKABLE_REGISTRIES = ImmutableMap.builder<ResourceKey<out Registry<*>>, NetworkedRegistryData<*>>().apply {
        put(this, ResourceKeys.BIOME, AquaBiome.NETWORK_CODEC)
        put(this, AquaResourceKeys.CHAT_TYPE, RichChatType.CODEC)
        put(this, AquaResourceKeys.DIMENSION_TYPE, AquaDimensionType.DIRECT_CODEC)
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
        val keyCodec: Codec<ResourceKey<out Registry<E>>> = Keys.CODEC.xmap({ AquaResourceKey.of(RegistryRoots.MINECRAFT, it) }, { it.location })
        val registryCodec: Codec<AquaRegistry<E>> = keyCodec.partialDispatch(
            "type",
            { DataResult.success(it.key) },
            { key -> getNetworkCodec(key).map { RegistryCodecs.network(key, it) } }
        )
        val mapCodec = Codec.map(keyCodec, registryCodec) as UnboundedMapCodec<out ResourceKey<out Registry<*>>, out AquaRegistry<*>>
        return captureMap(mapCodec)
    }

    @JvmStatic
    private fun <K : ResourceKey<out Registry<*>>, V : AquaRegistry<*>> captureMap(codec: UnboundedMapCodec<K, V>): Codec<RegistryHolder> {
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
        val statics = AquaRegistries.StaticHolder
        val networked = networkedRegistries(dynamic)
        return CombinedRegistryHolder(statics, networked)
    }

    @JvmRecord
    private data class NetworkedRegistryData<E>(val key: ResourceKey<out Registry<E>>, val networkCodec: Codec<E>)
}
