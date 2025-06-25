package net.aquamine.server.effect.particle.data

import net.aquamine.api.effect.particle.data.ColorParticleData
import net.aquamine.api.util.Color

@JvmRecord
data class AquaColorParticleData(override val color: Color) : ColorParticleData
