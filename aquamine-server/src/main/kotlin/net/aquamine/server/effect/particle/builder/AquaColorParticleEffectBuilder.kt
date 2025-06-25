package net.aquamine.server.effect.particle.builder

import net.aquamine.api.effect.particle.ColorParticleType
import net.aquamine.api.effect.particle.builder.ColorParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.server.effect.particle.data.AquaColorParticleData

class AquaColorParticleEffectBuilder(type: ColorParticleType) : AbstractColorParticleEffectBuilder<ApiColor>(type), ApiColor {

    override fun buildData(): ParticleData = AquaColorParticleData(color)
}

private typealias ApiColor = ColorParticleEffectBuilder
