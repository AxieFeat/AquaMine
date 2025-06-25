package net.aquamine.server.effect.particle

import net.aquamine.api.effect.particle.ParticleEffect
import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.api.util.Vec3d

@JvmRecord
data class AquaParticleEffect(
    override val type: ParticleType,
    override val quantity: Int,
    override val offset: Vec3d,
    override val longDistance: Boolean,
    override val data: ParticleData? = null
) : ParticleEffect
