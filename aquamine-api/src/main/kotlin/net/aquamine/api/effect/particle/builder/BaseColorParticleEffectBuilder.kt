package net.aquamine.api.effect.particle.builder

import net.aquamine.annotations.dsl.ParticleDsl
import net.aquamine.api.util.Color
import org.jetbrains.annotations.Contract

/**
 * The base builder for building color particle effects.
 */
@ParticleDsl
interface BaseColorParticleEffectBuilder<B : BaseColorParticleEffectBuilder<B>> : BaseParticleEffectBuilder<B> {

    /**
     * Sets the color of the particle to the given [color].
     *
     * @param color The color.
     *
     * @return This builder.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun color(color: Color): B
}
