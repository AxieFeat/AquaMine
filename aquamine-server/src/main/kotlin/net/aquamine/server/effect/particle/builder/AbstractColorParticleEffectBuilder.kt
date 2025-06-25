package net.aquamine.server.effect.particle.builder

import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.particle.builder.BaseColorParticleEffectBuilder
import net.aquamine.api.util.Color

@Suppress("UNCHECKED_CAST")
abstract class AbstractColorParticleEffectBuilder<B : BaseColor<B>>(type: ParticleType) : AbstractParticleEffectBuilder<B>(type), BaseColor<B> {

    protected var color = Color.BLACK

    override fun color(color: Color): B = apply { this.color = color } as B
}

private typealias BaseColor<B> = BaseColorParticleEffectBuilder<B>
