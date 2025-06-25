package net.aquamine.server.effect.particle.builder

import net.aquamine.api.effect.particle.DustTransitionParticleType
import net.aquamine.api.effect.particle.builder.DustTransitionParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.api.util.Color
import net.aquamine.server.effect.particle.data.AquaDustTransitionParticleData

class KryptonDustTransitionParticleEffectBuilder(type: DustTransitionParticleType) : AbstractDustParticleEffectBuilder<ApiDT>(type), ApiDT {

    private var to: Color = Color.BLACK

    override fun toColor(color: Color): ApiDT = apply { to = color }

    override fun buildData(): ParticleData = AquaDustTransitionParticleData(color, scale, to)
}

private typealias ApiDT = DustTransitionParticleEffectBuilder
