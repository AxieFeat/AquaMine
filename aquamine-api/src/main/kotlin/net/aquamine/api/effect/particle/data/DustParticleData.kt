package net.aquamine.api.effect.particle.data

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.util.Color
import org.jetbrains.annotations.Contract

/**
 * Holds data for dust particle effects.
 */
@ImmutableType
interface DustParticleData : ColorParticleData {

    /**
     * The scale of the dust.
     */
    val scale: Float

    companion object {

        /**
         * Creates new dust particle data with the given [color] and [scale].
         *
         * @param color The color.
         * @param scale The scale.
         *
         * @return New dust particle data.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        fun of(color: Color, scale: Float): DustParticleData = ParticleData.factory().dust(color, scale)
    }
}
