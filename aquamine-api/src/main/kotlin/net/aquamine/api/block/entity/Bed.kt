package net.aquamine.api.block.entity

import net.aquamine.api.item.data.DyeColor

/**
 * A bed.
 */
interface Bed : BlockEntity {

    /**
     * The color of this bed.
     */
    val color: DyeColor
}
