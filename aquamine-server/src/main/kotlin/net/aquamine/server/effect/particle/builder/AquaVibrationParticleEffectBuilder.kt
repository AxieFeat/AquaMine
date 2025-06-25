package net.aquamine.server.effect.particle.builder

import net.aquamine.api.effect.particle.VibrationParticleType
import net.aquamine.api.effect.particle.builder.VibrationParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.api.util.Vec3d
import net.aquamine.server.effect.particle.data.AquaVibrationParticleData

class AquaVibrationParticleEffectBuilder(type: VibrationParticleType) : AbstractParticleEffectBuilder<ApiVibration>(type), ApiVibration {

    private var destination: Vec3d = Vec3d.ZERO
    private var ticks = 0

    override fun destination(position: Vec3d): ApiVibration = apply { destination = position }

    override fun ticks(ticks: Int): ApiVibration = apply { this.ticks = ticks }

    override fun buildData(): ParticleData = AquaVibrationParticleData(destination, ticks)
}

private typealias ApiVibration = VibrationParticleEffectBuilder
