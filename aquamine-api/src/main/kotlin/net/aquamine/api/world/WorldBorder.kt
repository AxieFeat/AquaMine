package net.aquamine.api.world

/**
 * The area around the world that prevents players from venturing too far into
 * the wilderness.
 */
interface WorldBorder {

    /**
     * The size, or diameter, of the world border.
     */
    val size: Double

    /**
     * The X coordinate of the center position of this world border.
     */
    val centerX: Double

    /**
     * The Z coordinate of the center position of this world border.
     */
    val centerZ: Double

    /**
     * The damage multiplier for this border.
     */
    val damageMultiplier: Double
}
