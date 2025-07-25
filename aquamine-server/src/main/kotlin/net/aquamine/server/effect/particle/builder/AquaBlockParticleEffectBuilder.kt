package net.aquamine.server.effect.particle.builder

import net.aquamine.api.block.BlockState
import net.aquamine.api.effect.particle.BlockParticleType
import net.aquamine.api.effect.particle.builder.BlockParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.server.effect.particle.data.AquaBlockParticleData
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.block.state.downcast

class AquaBlockParticleEffectBuilder(type: BlockParticleType) : AbstractParticleEffectBuilder<ApiBlock>(type), ApiBlock {

    private var block: AquaBlockState = AquaBlocks.STONE.defaultState

    override fun block(block: BlockState): ApiBlock = apply { this.block = block.downcast() }

    override fun buildData(): ParticleData = AquaBlockParticleData(block)
}

private typealias ApiBlock = BlockParticleEffectBuilder
