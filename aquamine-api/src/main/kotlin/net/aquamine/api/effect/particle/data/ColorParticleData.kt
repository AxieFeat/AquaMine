package net.aquamine.api.effect.particle.data

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.util.Color
import org.jetbrains.annotations.Contract

/**
 * Holds data for colored particle effects.
 */
@ImmutableType
interface ColorParticleData : ParticleData {

    /**
     * The color of the particle.
     */
    val color: Color

    companion object {

        /**
         * Creates new color particle data with the given [color].
         *
         * @param color The color.
         *
         * @return New colour particle data.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun of(color: Color): ColorParticleData = ParticleData.factory().color(color)
    }
}
