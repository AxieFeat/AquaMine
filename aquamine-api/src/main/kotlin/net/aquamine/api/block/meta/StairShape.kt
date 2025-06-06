package net.aquamine.api.block.meta

/**
 * Indicates the shape that a stair block may appear in to ensure it properly
 * connects to its neighboring stair blocks.
 */
enum class StairShape {

    /**
     * The stairs do not bend at all. They appear in a straight line, where
     * the end faces are on opposing sides.
     */
    STRAIGHT,

    /**
     * The stairs turn inwards to the left. The missing section of the stairs
     * will only occupy 1/8 of the full block, rather than 1/4 (2/8), like the
     * straight stairs.
     *
     * The turn will be in the opposite direction to the [INNER_RIGHT] turn.
     */
    INNER_LEFT,

    /**
     * The stairs turn inwards to the right. The missing section of the stairs
     * will only occupy 1/8 of the full block, rather than 1/4 (2/8), like the
     * straight stairs.
     *
     * The turn will be in the opposite direction to the [INNER_LEFT] turn.
     */
    INNER_RIGHT,

    /**
     * The stairs will turn outwards to the left. The missing section of the
     * stairs will occupy 3/8 of the full block, rather than 1/4 (2/8), like
     * the straight stairs.
     *
     * The turn will be in the opposite direction to the [OUTER_RIGHT] turn.
     */
    OUTER_LEFT,

    /**
     * The stairs will turn outwards to the right. The missing section of the
     * stairs will occupy 3/8 of the full block, rather than 1/4 (2/8), like
     * the straight stairs.
     *
     * The turn will be in the opposite direction to the [OUTER_LEFT] turn.
     */
    OUTER_RIGHT
}
