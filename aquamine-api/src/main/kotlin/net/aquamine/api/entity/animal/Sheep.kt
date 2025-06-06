package net.aquamine.api.entity.animal

import net.aquamine.api.item.data.DyeColor

/**
 * A sheep.
 */
interface Sheep : Animal {

    /**
     * If this sheep has been sheared.
     */
    var isSheared: Boolean

    /**
     * The color of this sheep's wool.
     */
    var woolColor: DyeColor
}
