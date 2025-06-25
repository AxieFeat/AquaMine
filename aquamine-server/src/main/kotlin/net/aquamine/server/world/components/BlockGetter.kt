package net.aquamine.server.world.components

import net.aquamine.api.block.BlockContainer
import net.aquamine.api.util.Vec3i
import net.aquamine.server.world.block.KryptonBlocks
import net.aquamine.server.world.block.entity.KryptonBlockEntity
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.fluid.KryptonFluidState
import net.aquamine.server.world.fluid.KryptonFluids

interface BlockGetter : HeightAccessor, BlockContainer, FluidGetter, BlockEntityGetter {

    fun maximumLightLevel(): Int = 15

    override fun getBlock(x: Int, y: Int, z: Int): KryptonBlockState

    override fun getBlock(position: Vec3i): KryptonBlockState = getBlock(position.x, position.y, position.z)

    object Empty : BlockGetter {

        override fun height(): Int = 0

        override fun minimumBuildHeight(): Int = 0

        override fun getBlock(x: Int, y: Int, z: Int): KryptonBlockState = KryptonBlocks.AIR.defaultState

        override fun getFluid(x: Int, y: Int, z: Int): KryptonFluidState = KryptonFluids.EMPTY.defaultState

        override fun getBlockEntity(x: Int, y: Int, z: Int): KryptonBlockEntity? = null
    }
}
