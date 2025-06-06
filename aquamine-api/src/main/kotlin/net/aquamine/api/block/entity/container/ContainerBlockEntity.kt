package net.aquamine.api.block.entity.container

import net.aquamine.api.block.entity.BlockEntity
import net.aquamine.api.inventory.Inventory

/**
 * A block entity that has a container.
 */
interface ContainerBlockEntity : BlockEntity {

    /**
     * The inventory that this block entity has.
     */
    val inventory: Inventory

    /**
     * The token that this container block entity is locked with.
     */
    var lockToken: String?
}
