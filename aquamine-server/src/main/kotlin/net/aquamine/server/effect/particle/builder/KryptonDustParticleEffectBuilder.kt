package net.aquamine.server.effect.particle.builder

import net.aquamine.api.effect.particle.DustParticleType
import net.aquamine.api.effect.particle.builder.DustParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.server.effect.particle.data.AquaDustParticleData

class KryptonDustParticleEffectBuilder(type: DustParticleType) : AbstractDustParticleEffectBuilder<ApiDust>(type), ApiDust {

    override fun buildData(): ParticleData = AquaDustParticleData(color, scale)
}

private typealias ApiDust = DustParticleEffectBuilder
