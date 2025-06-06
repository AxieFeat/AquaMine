package net.aquamine.api.block.meta

/**
 * Indicates the shape of a minecart rail this property is applied to
 * represent.
 *
 * Minecart rails can be in one of three layouts:
 * * Straight flat, which will keep the Minecart on the same Y level and
 *   direction of travel. These rails can either be north to south or east to
 *   west.
 * * Straight ascending, which will ascend the Minecart up by one Y level and
 *   maintain the same direction of travel.
 * * Corner, which will change the Minecart's orientation by 90 degrees,
 *   either clockwise or anticlockwise, depending on the direction change.
 */
enum class RailShape {

    NORTH_SOUTH,
    EAST_WEST,
    ASCENDING_NORTH,
    ASCENDING_SOUTH,
    ASCENDING_EAST,
    ASCENDING_WEST,
    NORTH_EAST,
    NORTH_WEST,
    SOUTH_EAST,
    SOUTH_WEST;

    /**
     * Gets whether this rail shape ascends the Minecart by one Y level.
     *
     * @return `true` if ascending, `false` otherwise.
     */
    fun isAscending(): Boolean = this == ASCENDING_NORTH || this == ASCENDING_SOUTH || this == ASCENDING_EAST || this == ASCENDING_WEST
}
