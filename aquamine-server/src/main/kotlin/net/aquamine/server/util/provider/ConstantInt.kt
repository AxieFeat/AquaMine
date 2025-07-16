package net.aquamine.server.util.provider

import net.aquamine.server.util.random.RandomSource
import net.aquamine.serialization.Codec
import net.aquamine.serialization.codecs.RecordCodecBuilder
import net.aquamine.util.Either

class ConstantInt private constructor(val value: Int) : IntProvider() {

    override val type: IntProviderType<*>
        get() = IntProviderTypes.CONSTANT
    override val minimumValue: Int
        get() = value
    override val maximumValue: Int
        get() = value

    override fun sample(random: RandomSource): Int = value

    override fun toString(): String = value.toString()

    companion object {

        @JvmField
        val ZERO: ConstantInt = ConstantInt(0)
        @JvmField
        val CODEC: Codec<Either<Int, ConstantInt>> = Codec.either(
            Codec.INT,
            RecordCodecBuilder.create { instance -> instance.group(Codec.INT.fieldOf("value").getting { it.value }).apply(instance) { of(it) } }
        )/*.xmap({ it.map(ConstantInt::of, Function.identity()) }, { Either.left(it.value) })*/
        // For why the above is commented out, see: https://youtrack.jetbrains.com/issue/KT-53478

        @JvmStatic
        fun of(value: Int): ConstantInt = ConstantInt(value)
    }
}
