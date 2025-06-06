package net.aquamine.api.effect.particle.data

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.util.Color
import org.jetbrains.annotations.Contract

/**
 * Holds data for dust colour transition particle effects.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
interface DustTransitionParticleData : DustParticleData {

    /**
     * The destination color of the particle.
     */
    @get:JvmName("toColor")
    val toColor: Color

    companion object {

        /**
         * Creates new dust colour transition particle data with the given
         * [from] source colour, [scale], and [to] destination colour.
         *
         * @param from The from color.
         * @param scale The scale.
         * @param to The to color.
         *
         * @return New dust transition particle data.
         */
        @JvmStatic
        @Contract("_, _, _ -> new", pure = true)
        fun of(from: Color, scale: Float, to: Color): DustTransitionParticleData = ParticleData.factory()
            .transition(from, scale, to)
    }
}
