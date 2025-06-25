package net.aquamine.server.effect.particle.builder

import net.aquamine.api.effect.particle.SimpleParticleType
import net.aquamine.api.effect.particle.builder.SimpleParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData

class KryptonSimpleParticleEffectBuilder(type: SimpleParticleType) : AbstractParticleEffectBuilder<ApiSimple>(type), ApiSimple {

    override fun buildData(): ParticleData? = null
}

private typealias ApiSimple = SimpleParticleEffectBuilder
