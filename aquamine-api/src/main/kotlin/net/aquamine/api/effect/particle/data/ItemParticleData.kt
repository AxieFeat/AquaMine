package net.aquamine.api.effect.particle.data

import net.aquamine.annotations.ImmutableType
import org.jetbrains.annotations.Contract
import net.aquamine.api.item.ItemStack

/**
 * Holds data for item particle effects.
 */
@ImmutableType
interface ItemParticleData : ParticleData {

    /**
     * The item that is shown.
     */
    val item: ItemStack

    companion object {

        /**
         * Creates new item particle data with the given [item].
         *
         * @param item The item that is shown.
         *
         * @return New item particle data.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun of(item: ItemStack): ItemParticleData = ParticleData.factory().item(item)
    }
}
