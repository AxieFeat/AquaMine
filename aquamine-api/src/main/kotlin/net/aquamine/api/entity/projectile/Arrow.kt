package net.aquamine.api.entity.projectile

import net.aquamine.api.util.Color

/**
 * An arrow or tipped arrow.
 */
interface Arrow : ArrowLike {

    /**
     * The color of this arrow.
     */
    var color: Color
}
