package net.aquamine.api.effect.particle.data

import net.aquamine.annotations.ImmutableType
import org.jetbrains.annotations.Contract
import net.aquamine.api.util.Vec3d

/**
 * Holds data for vibration particle effects.
 */
@ImmutableType
interface VibrationParticleData : ParticleData {

    /**
     * The ending position of the vibration.
     */
    val destination: Vec3d

    /**
     * The time, in ticks, it will take for the vibration to vibrate from its
     * starting position to the [destination].
     */
    val ticks: Int

    companion object {

        /**
         * Creates new vibration particle effect data with the given
         * [destination] and [ticks].
         *
         * @param destination The ending position.
         * @param ticks The time, in ticks, it takes for the vibration to finish.
         *
         * @return New vibration particle effect data.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun of(destination: Vec3d, ticks: Int): VibrationParticleData = ParticleData.factory()
            .vibration(destination, ticks)
    }
}
