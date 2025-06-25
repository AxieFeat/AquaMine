package net.aquamine.server.effect.particle.data

import net.aquamine.api.effect.particle.data.DirectionalParticleData
import net.aquamine.api.util.Vec3d

@JvmRecord
data class AquaDirectionalParticleData(override val direction: Vec3d?, override val velocity: Float) : DirectionalParticleData
