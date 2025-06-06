package net.aquamine.api.block.meta

import net.aquamine.api.util.Direction

/**
 * Indicates the orientation of a block that may be oriented in two directions
 * on two separate axes, such as a jigsaw block.
 *
 * @property top The top part of the direction.
 * @property front The front part of the direction.
 */
enum class Orientation(val top: Direction, val front: Direction) {

    UP_NORTH(Direction.UP, Direction.NORTH),
    UP_SOUTH(Direction.UP, Direction.SOUTH),
    UP_EAST(Direction.UP, Direction.EAST),
    UP_WEST(Direction.UP, Direction.WEST),
    DOWN_NORTH(Direction.DOWN, Direction.NORTH),
    DOWN_SOUTH(Direction.DOWN, Direction.SOUTH),
    DOWN_EAST(Direction.DOWN, Direction.EAST),
    DOWN_WEST(Direction.DOWN, Direction.WEST),
    NORTH_UP(Direction.NORTH, Direction.UP),
    SOUTH_UP(Direction.SOUTH, Direction.UP),
    EAST_UP(Direction.EAST, Direction.UP),
    WEST_UP(Direction.WEST, Direction.UP)
}
