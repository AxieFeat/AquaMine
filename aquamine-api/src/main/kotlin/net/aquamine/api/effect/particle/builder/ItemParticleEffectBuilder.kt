package net.aquamine.api.effect.particle.builder

import net.aquamine.annotations.dsl.ParticleDsl
import org.jetbrains.annotations.Contract
import net.aquamine.api.item.ItemStack

/**
 * A builder for building item particle effects.
 */
@ParticleDsl
interface ItemParticleEffectBuilder : BaseParticleEffectBuilder<ItemParticleEffectBuilder> {

    /**
     * Sets the item data of the texture to be used.
     *
     * @param item The item type to use.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun item(item: ItemStack): ItemParticleEffectBuilder
}
