package net.aquamine.server.world.biome.data

import net.aquamine.api.world.biome.Climate
import net.aquamine.api.world.biome.Precipitation
import net.aquamine.api.world.biome.TemperatureModifier
import net.aquamine.server.util.serialization.EnumCodecs
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.MapCodec
import org.kryptonmc.serialization.codecs.RecordCodecBuilder

@JvmRecord
data class AquaClimate(
    override val precipitation: Precipitation,
    override val temperature: Float,
    override val downfall: Float,
    override val temperatureModifier: TemperatureModifier = TemperatureModifier.NONE
) : Climate {

    class Builder : Climate.Builder {

        private var precipitation = Precipitation.NONE
        private var temperature = 0F
        private var downfall = 0F
        private var temperatureModifier = TemperatureModifier.NONE

        override fun precipitation(precipitation: Precipitation): Climate.Builder = apply { this.precipitation = precipitation }

        override fun temperature(temperature: Float): Climate.Builder = apply { this.temperature = temperature }

        override fun downfall(downfall: Float): Climate.Builder = apply { this.downfall = downfall }

        override fun temperatureModifier(modifier: TemperatureModifier): Climate.Builder = apply { temperatureModifier = modifier }

        override fun build(): Climate = AquaClimate(precipitation, temperature, downfall, temperatureModifier)
    }

    object Factory : Climate.Factory {

        override fun builder(): Climate.Builder = Builder()
    }

    companion object {

        @JvmField
        val DEFAULT: Climate = Builder().build()

        @JvmField
        val CODEC: MapCodec<Climate> = RecordCodecBuilder.createMap { instance ->
            instance.group(
                EnumCodecs.PRECIPITATION.fieldOf("precipitation").getting { it.precipitation },
                Codec.FLOAT.fieldOf("temperature").getting { it.temperature },
                Codec.FLOAT.fieldOf("downfall").getting { it.downfall },
                EnumCodecs.TEMPERATURE_MODIFIER.fieldOf("temperature_modifier").getting { it.temperatureModifier }
            ).apply(instance, ::AquaClimate)
        }
    }
}
