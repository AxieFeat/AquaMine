package net.aquamine.api.effect.particle.builder

import net.aquamine.annotations.dsl.ParticleDsl
import org.jetbrains.annotations.Contract

/**
 * The base builder for building dust particle effects.
 */
@ParticleDsl
interface BaseDustParticleEffectBuilder<B : BaseDustParticleEffectBuilder<B>> : BaseColorParticleEffectBuilder<B> {

    /**
     * Sets the scale of the dust particles.
     *
     * @param scale The scale of the particles.
     *
     * @return This builder.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun scale(scale: Float): B
}
