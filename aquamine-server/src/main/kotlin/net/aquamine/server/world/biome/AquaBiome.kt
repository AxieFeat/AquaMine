package net.aquamine.server.world.biome

import net.kyori.adventure.key.Key
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.api.world.biome.Biome
import net.aquamine.api.world.biome.BiomeEffects
import net.aquamine.api.world.biome.Climate
import net.aquamine.server.registry.AquaDynamicRegistries
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.registry.holder.HolderSet
import net.aquamine.server.registry.network.RegistryCodecs
import net.aquamine.server.registry.network.RegistryFileCodec
import net.aquamine.server.world.biome.data.AquaBiomeEffects
import net.aquamine.server.world.biome.data.AquaClimate
import net.aquamine.serialization.Codec
import net.aquamine.serialization.codecs.RecordCodecBuilder

class AquaBiome(override val climate: Climate, override val effects: BiomeEffects) : Biome {

    override fun key(): Key = AquaDynamicRegistries.BIOME.getKey(this) ?: UNREGISTERED_KEY

    class Builder : Biome.Builder {

        private var climate = AquaClimate.DEFAULT
        private var effects = AquaBiomeEffects.DEFAULT

        override fun climate(climate: Climate): Builder = apply { this.climate = climate }

        inline fun climate(builder: Climate.Builder.() -> Unit): Builder = climate(AquaClimate.Builder().apply(builder).build())

        override fun effects(effects: BiomeEffects): Builder = apply { this.effects = effects }

        inline fun effects(builder: BiomeEffects.Builder.() -> Unit): Builder = effects(AquaBiomeEffects.Builder().apply(builder).build())

        override fun build(): AquaBiome = AquaBiome(climate, effects)
    }

    object Factory : Biome.Factory {

        override fun builder(): Builder = Builder()
    }

    companion object {

        private val UNREGISTERED_KEY = Key.key("aquamine", "unregistered_biome")

        // When we add mob spawn and biome generation settings, this will actually differ from the network codec
        @JvmField
        val DIRECT_CODEC: Codec<Biome> = RecordCodecBuilder.create { instance ->
            instance.group(
                AquaClimate.CODEC.getting { it.climate },
                AquaBiomeEffects.CODEC.fieldOf("effects").getting { it.effects }
            ).apply(instance, ::AquaBiome)
        }
        @JvmField
        val NETWORK_CODEC: Codec<Biome> = RecordCodecBuilder.create { instance ->
            instance.group(
                AquaClimate.CODEC.getting { it.climate },
                AquaBiomeEffects.CODEC.fieldOf("effects").getting { it.effects }
            ).apply(instance, ::AquaBiome)
        }
        @JvmField
        val CODEC: Codec<Holder<Biome>> = RegistryFileCodec.create(ResourceKeys.BIOME, DIRECT_CODEC)
        @JvmField
        val LIST_CODEC: Codec<HolderSet<Biome>> = RegistryCodecs.homogenousList(ResourceKeys.BIOME, DIRECT_CODEC)
    }
}
