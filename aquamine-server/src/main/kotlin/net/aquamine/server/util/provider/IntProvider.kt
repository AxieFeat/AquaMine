package net.aquamine.server.util.provider

import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.util.random.RandomSource
import net.aquamine.serialization.Codec
import net.aquamine.serialization.DataResult
import net.aquamine.util.Either
import java.util.function.Function

@Suppress("UnnecessaryAbstractClass")
abstract class IntProvider {

    abstract val type: IntProviderType<*>
    abstract val minimumValue: Int
    abstract val maximumValue: Int

    abstract fun sample(random: RandomSource): Int

    companion object {

        private val CONSTANT_OR_DISPATCH_CODEC = Codec.either(
            Codec.INT,
            AquaRegistries.INT_PROVIDER_TYPE.byNameCodec().dispatch({ it.type }, { it.codec() })
        )
        @JvmField
        val CODEC: Codec<IntProvider> = CONSTANT_OR_DISPATCH_CODEC.xmap(
            { either -> either.map({ ConstantInt.of(it) }, Function.identity()) },
            { if (it is ConstantInt) Either.left(it.value) else Either.right(it) }
        )

        @JvmStatic
        fun codec(min: Int, max: Int): Codec<IntProvider> {
            val checker = Function<IntProvider, DataResult<IntProvider>> {
                if (it.minimumValue < min) return@Function DataResult.error("Int provider lower bound too low! Minimum value must be >= $min!")
                if (it.maximumValue > max) return@Function DataResult.error("Int provider upper bound too high! Maximum value must be <= $max!")
                DataResult.success(it)
            }
            return CODEC.flatXmap(checker, checker)
        }
    }
}
