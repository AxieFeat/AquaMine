package net.aquamine.api.effect.particle.data

import net.aquamine.annotations.ImmutableType
import org.jetbrains.annotations.Contract
import net.aquamine.api.util.Vec3d

/**
 * Holds data for directional particle effects.
 */
@ImmutableType
interface DirectionalParticleData : ParticleData {

    /**
     * The direction the particle will travel.
     *
     * If this value is null, it will be randomized.
     */
    val direction: Vec3d?

    /**
     * The current velocity of the particle in the direction it is moving.
     *
     * If this value is zero, the particle is not moving.
     */
    val velocity: Float

    companion object {

        /**
         * Creates new directional particle data with the given [direction] and
         * [velocity].
         *
         * @param direction The direction the particle will travel.
         * @param velocity The velocity of the particle.
         *
         * @return New directional particle data.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        fun of(direction: Vec3d?, velocity: Float): DirectionalParticleData = ParticleData.factory()
            .directional(direction, velocity)
    }
}
