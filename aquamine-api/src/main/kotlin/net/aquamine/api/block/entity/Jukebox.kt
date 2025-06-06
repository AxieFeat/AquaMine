package net.aquamine.api.block.entity

import net.aquamine.api.item.ItemStack

/**
 * A jukebox.
 */
interface Jukebox : BlockEntity {

    /**
     * The record that this jukebox is currently playing.
     */
    var record: ItemStack
}
