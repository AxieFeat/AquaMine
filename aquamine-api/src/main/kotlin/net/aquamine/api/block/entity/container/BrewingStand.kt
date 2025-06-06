package net.aquamine.api.block.entity.container

import net.aquamine.api.block.entity.NameableBlockEntity

/**
 * A brewing stand.
 */
interface BrewingStand : ContainerBlockEntity, NameableBlockEntity {

    /**
     * The amount of time, in ticks, until the fuel in the brewing stand runs
     * out.
     */
    var remainingFuel: Int

    /**
     * The amount of time, in ticks, until the potions being brewed in this
     * brewing stand are brewed.
     */
    var remainingBrewTime: Int

    /**
     * Brews the potions in this brewing stand.
     *
     * @return If the potions were successfully brewed.
     */
    fun brew(): Boolean
}
