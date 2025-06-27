package net.aquamine.server.world.components

import net.aquamine.api.block.BlockContainer
import net.aquamine.api.util.Vec3i
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.entity.AquaBlockEntity
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.fluid.AquaFluidState
import net.aquamine.server.world.fluid.AquaFluids

interface BlockGetter : HeightAccessor, BlockContainer, FluidGetter, BlockEntityGetter {

    fun maximumLightLevel(): Int = 15

    override fun getBlock(x: Int, y: Int, z: Int): AquaBlockState

    override fun getBlock(position: Vec3i): AquaBlockState = getBlock(position.x, position.y, position.z)

    object Empty : BlockGetter {

        override fun height(): Int = 0

        override fun minimumBuildHeight(): Int = 0

        override fun getBlock(x: Int, y: Int, z: Int): AquaBlockState = AquaBlocks.AIR.defaultState

        override fun getFluid(x: Int, y: Int, z: Int): AquaFluidState = AquaFluids.EMPTY.defaultState

        override fun getBlockEntity(x: Int, y: Int, z: Int): AquaBlockEntity? = null
    }
}
