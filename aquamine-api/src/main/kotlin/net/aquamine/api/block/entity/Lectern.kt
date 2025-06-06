package net.aquamine.api.block.entity

import net.aquamine.api.item.ItemStack

/**
 * A lectern.
 */
interface Lectern : BlockEntity {

    /**
     * The book that is in this lectern.
     */
    var book: ItemStack
}
