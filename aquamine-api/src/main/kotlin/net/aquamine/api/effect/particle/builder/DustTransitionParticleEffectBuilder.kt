package net.aquamine.api.effect.particle.builder

import net.aquamine.annotations.dsl.ParticleDsl
import net.aquamine.api.util.Color
import org.jetbrains.annotations.Contract

/**
 * A builder for building dust transition particle effects.
 */
@ParticleDsl
interface DustTransitionParticleEffectBuilder : BaseDustParticleEffectBuilder<DustTransitionParticleEffectBuilder> {

    /**
     * Sets the color to transition the particle to the given [color].
     *
     * @param color The color.
     *
     * @return This builder.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun toColor(color: Color): DustTransitionParticleEffectBuilder
}
