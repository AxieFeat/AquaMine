package net.aquamine.api.entity

import net.aquamine.api.util.Color

/**
 * Represents an area effect cloud, such as that of the lingering effect
 * that appears when a lingering potion is thrown.
 */
interface AreaEffectCloud : Entity {

    /**
     * The duration, in ticks, that this area effect cloud will exist for.
     */
    var duration: Int

    /**
     * The radius of this area affect cloud.
     */
    var radius: Float

    /**
     * The color of this area affect cloud.
     */
    var color: Color
}
