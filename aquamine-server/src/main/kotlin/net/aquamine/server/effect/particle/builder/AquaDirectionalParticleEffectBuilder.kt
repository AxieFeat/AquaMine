package net.aquamine.server.effect.particle.builder

import net.aquamine.api.effect.particle.DirectionalParticleType
import net.aquamine.api.effect.particle.builder.DirectionalParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.api.util.Vec3d
import net.aquamine.server.effect.particle.data.AquaDirectionalParticleData

class AquaDirectionalParticleEffectBuilder(type: DirectionalParticleType) : AbstractParticleEffectBuilder<ApiDirectional>(type), ApiDirectional {

    private var direction: Vec3d? = null
    private var velocity = 0F

    override fun direction(direction: Vec3d): ApiDirectional = apply { this.direction = direction }

    override fun velocity(velocity: Float): ApiDirectional = apply { this.velocity = velocity }

    override fun buildData(): ParticleData = AquaDirectionalParticleData(direction, velocity)
}

private typealias ApiDirectional = DirectionalParticleEffectBuilder
