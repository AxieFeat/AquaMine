package net.aquamine.api.block.entity.container

import net.aquamine.api.block.entity.NameableBlockEntity

/**
 * A furnace block entity.
 */
interface FurnaceBlockEntity : ContainerBlockEntity, NameableBlockEntity {

    /**
     * The amount of time, in ticks, until the fuel in the furnace runs out.
     */
    var remainingFuel: Int

    /**
     * The total amount of time, in ticks, that the fuel in the furnace will
     * burn for.
     */
    val fuelDuration: Int

    /**
     * The current progress of the item being cooked.
     */
    var cookingProgress: Int

    /**
     * The amount of time, in ticks, until the item in the furnace is cooked.
     */
    val cookingDuration: Int
}
