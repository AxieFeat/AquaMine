package net.aquamine.server.effect.particle

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.particle.DustTransitionParticleType
import net.aquamine.api.effect.particle.builder.DustTransitionParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.server.effect.particle.builder.KryptonDustTransitionParticleEffectBuilder
import net.aquamine.server.effect.particle.data.AquaDustTransitionParticleData
import net.aquamine.server.network.buffer.BinaryReader

@JvmRecord
data class AquaDustTransitionParticleType(private val key: Key) : AquaParticleType, DustTransitionParticleType {

    override fun key(): Key = key

    override fun builder(): DustTransitionParticleEffectBuilder = KryptonDustTransitionParticleEffectBuilder(this)

    override fun createData(reader: BinaryReader): ParticleData = AquaDustTransitionParticleData(reader)
}
