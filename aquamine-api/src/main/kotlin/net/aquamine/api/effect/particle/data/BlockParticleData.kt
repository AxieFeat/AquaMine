package net.aquamine.api.effect.particle.data

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.block.BlockState
import org.jetbrains.annotations.Contract

/**
 * Holds data for block particle effects.
 */
@ImmutableType
interface BlockParticleData : ParticleData {

    /**
     * The block state that is shown.
     */
    val block: BlockState

    companion object {

        /**
         * Creates new block particle data with the given [block] state.
         *
         * @param block the block state that is shown
         * @return new block particle data
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun of(block: BlockState): BlockParticleData = ParticleData.factory().block(block)
    }
}
