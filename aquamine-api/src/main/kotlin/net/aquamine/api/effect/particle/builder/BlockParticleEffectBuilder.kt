package net.aquamine.api.effect.particle.builder

import net.aquamine.annotations.dsl.ParticleDsl
import net.aquamine.api.block.BlockState
import org.jetbrains.annotations.Contract

/**
 * A builder for building block particle effects.
 */
@ParticleDsl
interface BlockParticleEffectBuilder : BaseParticleEffectBuilder<BlockParticleEffectBuilder> {

    /**
     * Sets the block state of the texture to be used.
     *
     * @param block The block.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun block(block: BlockState): BlockParticleEffectBuilder
}
