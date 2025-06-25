package net.aquamine.server.effect.particle

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.particle.DustParticleType
import net.aquamine.api.effect.particle.builder.DustParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.server.effect.particle.builder.KryptonDustParticleEffectBuilder
import net.aquamine.server.effect.particle.data.AquaDustParticleData
import net.aquamine.server.network.buffer.BinaryReader

@JvmRecord
data class AquaDustParticleType(private val key: Key) : AquaParticleType, DustParticleType {

    override fun key(): Key = key

    override fun builder(): DustParticleEffectBuilder = KryptonDustParticleEffectBuilder(this)

    override fun createData(reader: BinaryReader): ParticleData = AquaDustParticleData(reader)
}
