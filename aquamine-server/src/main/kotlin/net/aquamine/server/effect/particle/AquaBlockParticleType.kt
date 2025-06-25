package net.aquamine.server.effect.particle

import net.aquamine.api.effect.particle.BlockParticleType
import net.aquamine.api.effect.particle.builder.BlockParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.server.effect.particle.builder.AquaBlockParticleEffectBuilder
import net.aquamine.server.effect.particle.data.AquaBlockParticleData
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.world.block.KryptonBlock
import net.kyori.adventure.key.Key

@JvmRecord
data class AquaBlockParticleType(private val key: Key) : AquaParticleType, BlockParticleType {

    override fun key(): Key = key

    override fun builder(): BlockParticleEffectBuilder = AquaBlockParticleEffectBuilder(this)

    override fun createData(reader: BinaryReader): ParticleData = AquaBlockParticleData(KryptonBlock.stateFromId(reader.readVarInt()))
}
