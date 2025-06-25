package net.aquamine.server.effect.particle.builder

import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.particle.builder.BaseDustParticleEffectBuilder

@Suppress("UNCHECKED_CAST")
abstract class AbstractDustParticleEffectBuilder<B : BaseDust<B>>(type: ParticleType) : AbstractColorParticleEffectBuilder<B>(type), BaseDust<B> {

    protected var scale = 0F

    override fun scale(scale: Float): B = apply { this.scale = scale } as B
}

private typealias BaseDust<B> = BaseDustParticleEffectBuilder<B>
