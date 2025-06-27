package net.aquamine.server.world.block.handler

import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.components.BlockGetter

interface RedstoneDataProvider {

    fun isApplicableBlockType(name: String): Boolean

    fun isSignalSource(state: AquaBlockState): Boolean {
        return false
    }

    fun hasAnalogOutputSignal(state: AquaBlockState): Boolean {
        return false
    }

    fun getAnalogOutputSignal(state: AquaBlockState, world: AquaWorld, pos: Vec3i): Int {
        return 0
    }

    fun getSignal(state: AquaBlockState, world: BlockGetter, pos: Vec3i, direction: Direction): Int {
        return 0
    }

    fun getDirectSignal(state: AquaBlockState, world: BlockGetter, pos: Vec3i, direction: Direction): Int {
        return 0
    }
}
