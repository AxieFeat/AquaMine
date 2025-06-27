package net.aquamine.server.world.redstone

import org.apache.logging.log4j.LogManager
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.state.AquaBlockState
import java.util.ArrayDeque

/**
 * A neighbour updater that batches update requests and executes multiple updates at once.
 */
class BatchingNeighbourUpdater(private val world: AquaWorld, private val maxChainedUpdates: Int) : NeighbourUpdater {

    private val stack = ArrayDeque<NeighbourUpdates>()
    private val addedThisLayer = ArrayList<NeighbourUpdates>()
    private var count = 0

    override fun shapeUpdate(direction: Direction, state: AquaBlockState, pos: Vec3i, neighbourPos: Vec3i, flags: Int,
                             recursionLeft: Int) {
        addAndRun(pos, ShapeUpdate(direction, state, pos, neighbourPos, flags))
    }

    override fun neighbourChanged(pos: Vec3i, block: AquaBlock, neighbourPos: Vec3i) {
        addAndRun(pos, SimpleNeighbourUpdate(pos, block, neighbourPos))
    }

    override fun neighbourChanged(state: AquaBlockState, pos: Vec3i, block: AquaBlock, neighbourPos: Vec3i, moving: Boolean) {
        addAndRun(pos, FullNeighbourUpdate(state, pos, block, neighbourPos, moving))
    }

    override fun updateNeighboursAtExceptFromFacing(pos: Vec3i, block: AquaBlock, except: Direction?) {
        addAndRun(pos, MultiNeighbourUpdate(pos, block, except))
    }

    private fun addAndRun(pos: Vec3i, updates: NeighbourUpdates) {
        val haveUpdates = count > 0
        val tooManyUpdates = maxChainedUpdates >= 0 && count >= maxChainedUpdates
        ++count
        if (!tooManyUpdates) {
            if (haveUpdates) addedThisLayer.add(updates) else stack.push(updates)
        } else if (count - 1 == maxChainedUpdates) {
            LOGGER.error("Too many chained neighbour updates! Skipping the rest. First skipped position: $pos")
        }
        if (!haveUpdates) runUpdates()
    }

    private fun runUpdates() {
        try {
            while (!stack.isEmpty() || addedThisLayer.isNotEmpty()) {
                for (i in addedThisLayer.size - 1 downTo 0) {
                    stack.push(addedThisLayer.get(i))
                }
                addedThisLayer.clear()
                val topUpdate = stack.peek()
                while (addedThisLayer.isEmpty()) {
                    if (!topUpdate.runNext(world)) {
                        stack.pop()
                        break
                    }
                }
            }
        } finally {
            stack.clear()
            addedThisLayer.clear()
            count = 0
        }
    }

    private sealed interface NeighbourUpdates {

        fun runNext(world: AquaWorld): Boolean
    }

    private class FullNeighbourUpdate(private val state: AquaBlockState, private val pos: Vec3i, private val block: AquaBlock,
                                      private val neighbourPos: Vec3i, private val movedByPiston: Boolean) : NeighbourUpdates {

        override fun runNext(world: AquaWorld): Boolean {
            NeighbourUpdater.executeUpdate(world, state, pos, block, neighbourPos, movedByPiston)
            return false
        }
    }

    private class MultiNeighbourUpdate(private val sourcePos: Vec3i, private val sourceBlock: AquaBlock,
                                       private val skipDirection: Direction?) : NeighbourUpdates {

        private var index = 0

        init {
            if (NeighbourUpdater.UPDATE_ORDER[index] == skipDirection) ++index
        }

        override fun runNext(world: AquaWorld): Boolean {
            val relative = sourcePos.relative(NeighbourUpdater.UPDATE_ORDER[index++])
            val state = world.getBlock(relative)
            state.neighbourChanged(world, relative, sourceBlock, sourcePos, false)
            if (index < NeighbourUpdater.UPDATE_ORDER.size && NeighbourUpdater.UPDATE_ORDER[index] == skipDirection) ++index
            return index < NeighbourUpdater.UPDATE_ORDER.size
        }
    }

    private class ShapeUpdate(private val direction: Direction, private val state: AquaBlockState, private val pos: Vec3i,
                              private val neighbourPos: Vec3i, private val updateFlags: Int) : NeighbourUpdates {

        override fun runNext(world: AquaWorld): Boolean {
            NeighbourUpdater.executeShapeUpdate(world, direction, state, pos, neighbourPos, updateFlags, 512)
            return false
        }
    }

    private class SimpleNeighbourUpdate(private val pos: Vec3i, private val block: AquaBlock,
                                        private val neighbourPos: Vec3i) : NeighbourUpdates {

        override fun runNext(world: AquaWorld): Boolean {
            NeighbourUpdater.executeUpdate(world, world.getBlock(pos), pos, block, neighbourPos, false)
            return false
        }
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}
