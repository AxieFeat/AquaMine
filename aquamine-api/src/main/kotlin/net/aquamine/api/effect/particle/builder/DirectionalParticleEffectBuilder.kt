package net.aquamine.api.effect.particle.builder

import net.aquamine.annotations.dsl.ParticleDsl
import org.jetbrains.annotations.Contract
import net.aquamine.api.util.Vec3d

/**
 * A builder for building directional particle effects.
 */
@ParticleDsl
interface DirectionalParticleEffectBuilder : BaseParticleEffectBuilder<DirectionalParticleEffectBuilder> {

    /**
     * Sets the direction that the particle effect will travel.
     *
     * @param direction The direction.
     *
     * @return This builder.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun direction(direction: Vec3d): DirectionalParticleEffectBuilder

    /**
     * Sets the velocity that the particle effect will travel with.
     *
     * The actual velocity tends to vary largely for each particle type, so
     * it's quite arbitrary what this means.
     *
     * @param velocity The velocity.
     *
     * @return This builder.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun velocity(velocity: Float): DirectionalParticleEffectBuilder
}
