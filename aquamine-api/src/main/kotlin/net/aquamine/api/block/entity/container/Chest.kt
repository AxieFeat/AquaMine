package net.aquamine.api.block.entity.container

import net.aquamine.api.block.entity.NameableBlockEntity

/**
 * A chest.
 */
interface Chest : ContainerBlockEntity, NameableBlockEntity {

    /**
     * The chest that this chest is connected to, if this chest is a double
     * chest.
     */
    val connectedChest: Chest?
}
