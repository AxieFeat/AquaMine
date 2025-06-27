package net.aquamine.server.effect.particle

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.particle.ItemParticleType
import net.aquamine.api.effect.particle.builder.ItemParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.server.effect.particle.builder.AquaItemParticleEffectBuilder
import net.aquamine.server.effect.particle.data.AquaItemParticleData
import net.aquamine.server.network.buffer.BinaryReader

@JvmRecord
data class AquaItemParticleType(private val key: Key) : AquaParticleType, ItemParticleType {

    override fun key(): Key = key

    override fun builder(): ItemParticleEffectBuilder = AquaItemParticleEffectBuilder(this)

    override fun createData(reader: BinaryReader): ParticleData = AquaItemParticleData(reader.readItem())
}
