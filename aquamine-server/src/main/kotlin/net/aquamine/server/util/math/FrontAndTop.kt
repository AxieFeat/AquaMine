package net.aquamine.server.util.math

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.aquamine.api.util.Direction

enum class FrontAndTop(val front: Direction, val top: Direction) {

    DOWN_EAST(Direction.DOWN, Direction.EAST),
    DOWN_NORTH(Direction.DOWN, Direction.NORTH),
    DOWN_SOUTH(Direction.DOWN, Direction.SOUTH),
    DOWN_WEST(Direction.DOWN, Direction.WEST),
    UP_EAST(Direction.UP, Direction.EAST),
    UP_NORTH(Direction.UP, Direction.NORTH),
    UP_SOUTH(Direction.UP, Direction.SOUTH),
    UP_WEST(Direction.UP, Direction.WEST),
    WEST_UP(Direction.WEST, Direction.UP),
    EAST_UP(Direction.EAST, Direction.UP),
    NORTH_UP(Direction.NORTH, Direction.UP),
    SOUTH_UP(Direction.SOUTH, Direction.UP);

    companion object {

        private val LOOKUP_TOP_FRONT = Int2ObjectOpenHashMap<FrontAndTop>(values().size).apply {
            values().forEach { put(lookupKey(it.front, it.top), it) }
        }

        @JvmStatic
        fun fromFrontAndTop(front: Direction, top: Direction): FrontAndTop = LOOKUP_TOP_FRONT.get(lookupKey(front, top))

        @JvmStatic
        private fun lookupKey(front: Direction, top: Direction): Int = top.ordinal shl 3 or front.ordinal
    }
}
