package net.aquamine.server.util.serialization

import net.aquamine.api.world.biome.Precipitation
import net.aquamine.api.world.biome.GrassColorModifier
import net.aquamine.api.world.biome.TemperatureModifier
import org.kryptonmc.serialization.Codec
import java.util.function.Function
import java.util.function.Supplier

object EnumCodecs {

    private const val PRE_BUILT_LOOKUP_MAP_THRESHOLD = 16

    @JvmField
    val TEMPERATURE_MODIFIER: Codec<TemperatureModifier> = of { TemperatureModifier.values() }
    @JvmField
    val PRECIPITATION: Codec<Precipitation> = of { Precipitation.values() }
    @JvmField
    val GRASS_COLOR_MODIFIER: Codec<GrassColorModifier> = of { GrassColorModifier.values() }

    @JvmStatic
    fun <E : Enum<E>> of(valueSupplier: Supplier<Array<E>>): EnumCodec<E> = of(valueSupplier) { it.name.lowercase() }

    @JvmStatic
    fun <E : Enum<E>> of(valueSupplier: Supplier<Array<E>>, toName: Function<E, String>): EnumCodec<E> {
        val values = valueSupplier.get()
        // We only create a lookup map if the amount of values is large enough that the map would really be beneficial.
        if (values.size > PRE_BUILT_LOOKUP_MAP_THRESHOLD) {
            val valueMap = values.associateBy(toName::apply)
            return EnumCodec { valueMap.get(it) }
        }
        return EnumCodec { name -> values.firstOrNull { toName.apply(it) == name } }
    }
}
