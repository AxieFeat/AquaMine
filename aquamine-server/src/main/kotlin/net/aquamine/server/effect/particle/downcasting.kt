package net.aquamine.server.effect.particle

import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.server.util.downcastApiType

fun ParticleType.downcast(): AquaParticleType = downcastApiType("ParticleType")
