package net.aquamine.server.util.provider

import net.aquamine.server.util.math.Maths
import net.aquamine.server.util.random.RandomSource
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.codecs.RecordCodecBuilder

class UniformInt(override val minimumValue: Int, override val maximumValue: Int) : IntProvider() {

    override val type: IntProviderType<*>
        get() = IntProviderTypes.UNIFORM

    override fun sample(random: RandomSource): Int = Maths.randomBetween(random, minimumValue, maximumValue)

    override fun toString(): String = "[$minimumValue-$maximumValue]"

    companion object {

        @JvmField
        val CODEC: Codec<UniformInt> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("min_inclusive").getting { it.minimumValue },
                Codec.INT.fieldOf("max_inclusive").getting { it.maximumValue }
            ).apply(instance) { min, max -> UniformInt(min, max) }
        }/*.comapFlatMap({
            if (it.maximumValue >= it.minimumValue) return@comapFlatMap DataResult.success(it)
            DataResult.error("Maximum must be >= minimum! Maximum: ${it.maximumValue}, minimum: ${it.minimumValue}")
        }, Function.identity())*/
        // For why the above is commented out, see: https://youtrack.jetbrains.com/issue/KT-53478
    }
}
