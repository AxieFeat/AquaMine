package net.aquamine.server.world.block.handler

import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.components.BlockGetter

interface RedstoneDataProvider {

    fun isApplicableBlockType(name: String): Boolean

    fun isSignalSource(state: KryptonBlockState): Boolean {
        return false
    }

    fun hasAnalogOutputSignal(state: KryptonBlockState): Boolean {
        return false
    }

    fun getAnalogOutputSignal(state: KryptonBlockState, world: KryptonWorld, pos: Vec3i): Int {
        return 0
    }

    fun getSignal(state: KryptonBlockState, world: BlockGetter, pos: Vec3i, direction: Direction): Int {
        return 0
    }

    fun getDirectSignal(state: KryptonBlockState, world: BlockGetter, pos: Vec3i, direction: Direction): Int {
        return 0
    }
}
