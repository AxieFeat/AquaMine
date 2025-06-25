package net.aquamine.server.effect.particle

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.particle.VibrationParticleType
import net.aquamine.api.effect.particle.builder.VibrationParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.server.effect.particle.builder.AquaVibrationParticleEffectBuilder
import net.aquamine.server.effect.particle.data.AquaVibrationParticleData
import net.aquamine.server.network.buffer.BinaryReader

@JvmRecord
data class AquaVibrationParticleType(private val key: Key) : AquaParticleType, VibrationParticleType {

    override fun key(): Key = key

    override fun builder(): VibrationParticleEffectBuilder = AquaVibrationParticleEffectBuilder(this)

    override fun createData(reader: BinaryReader): ParticleData = AquaVibrationParticleData(reader)
}
