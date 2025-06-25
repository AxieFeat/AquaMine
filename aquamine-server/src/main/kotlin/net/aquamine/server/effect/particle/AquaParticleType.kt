package net.aquamine.server.effect.particle

import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.server.network.buffer.BinaryReader

interface AquaParticleType {

    fun createData(reader: BinaryReader): ParticleData? = null
}
