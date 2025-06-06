package net.aquamine.api.block.entity.container

import net.aquamine.api.block.entity.NameableBlockEntity

/**
 * A hopper.
 */
interface Hopper : ContainerBlockEntity, NameableBlockEntity {

    /**
     * The amount of time, in ticks, before the hopper can transfer another
     * item.
     */
    val cooldown: Int

    /**
     * Requests this hopper transfers the next item to a connected container.
     */
    fun transferItem()
}
