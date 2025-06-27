package net.aquamine.server.world.redstone

import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.components.WorldAccessor

/**
 * An abstraction over updating neighbours of a block.
 */
interface NeighbourUpdater {

    fun shapeUpdate(direction: Direction, state: AquaBlockState, pos: Vec3i, neighbourPos: Vec3i, flags: Int, recursionLeft: Int)

    fun neighbourChanged(pos: Vec3i, block: AquaBlock, neighbourPos: Vec3i)

    fun neighbourChanged(state: AquaBlockState, pos: Vec3i, block: AquaBlock, neighbourPos: Vec3i, moving: Boolean)

    fun updateNeighboursAtExceptFromFacing(pos: Vec3i, block: AquaBlock, except: Direction?) {
        UPDATE_ORDER.forEach { if (it != except) neighbourChanged(pos.relative(it), block, pos) }
    }

    companion object {

        @JvmField
        val UPDATE_ORDER: Array<Direction> = arrayOf(Direction.WEST, Direction.EAST, Direction.DOWN, Direction.UP, Direction.NORTH, Direction.SOUTH)

        @JvmStatic
        fun executeShapeUpdate(world: WorldAccessor, direction: Direction, state: AquaBlockState, pos: Vec3i, neighbourPos: Vec3i,
                               flags: Int, recursionLeft: Int) {
            val oldState = world.getBlock(pos)
            val newState = oldState.updateShape(direction, state, world, pos, neighbourPos)
            AquaBlock.updateOrDestroy(oldState, newState, world, pos, flags, recursionLeft)
        }

        @JvmStatic
        fun executeUpdate(world: AquaWorld, state: AquaBlockState, pos: Vec3i, block: AquaBlock, neighbourPos: Vec3i,
                          moving: Boolean) {
            state.neighbourChanged(world, pos, block, neighbourPos, moving)
        }
    }
}
