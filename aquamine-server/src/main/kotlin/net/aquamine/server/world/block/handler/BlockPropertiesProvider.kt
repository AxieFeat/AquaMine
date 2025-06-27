package net.aquamine.server.world.block.handler

import net.aquamine.api.block.PushReaction
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.item.context.BlockPlaceContext
import net.aquamine.server.world.block.data.BlockOffsetType
import net.aquamine.server.world.block.data.RenderShape
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.components.BlockGetter
import net.aquamine.server.world.fluid.AquaFluid
import net.aquamine.server.world.fluid.AquaFluidState
import net.aquamine.server.world.fluid.AquaFluids
import net.aquamine.server.world.material.MaterialColor
import net.aquamine.server.world.material.MaterialColors

interface BlockPropertiesProvider {

    fun isApplicableBlockType(name: String): Boolean

    fun getRenderShape(state: AquaBlockState): RenderShape {
        return RenderShape.MODEL
    }

    fun getPushReaction(state: AquaBlockState): PushReaction {
        return state.material.pushReaction
    }

    fun getFluidState(state: AquaBlockState): AquaFluidState = AquaFluids.EMPTY.defaultState

    fun getItemStack(world: BlockGetter, pos: Vec3i, state: AquaBlockState): AquaItemStack {
        return AquaItemStack.EMPTY
    }

    fun useShapeForLightOcclusion(state: AquaBlockState): Boolean {
        return false
    }

    fun getLightBlock(state: AquaBlockState, world: BlockGetter, pos: Vec3i): Int {
        if (state.isSolidRender(world, pos)) return world.maximumLightLevel()
        return if (state.propagatesSkylightDown(world, pos)) 0 else 1
    }

    fun canBeReplaced(state: AquaBlockState, context: BlockPlaceContext): Boolean {
        return state.material.replaceable && (context.item.isEmpty() || !context.item.eq(state.block.asItem()))
    }

    fun canBeReplaced(state: AquaBlockState, fluid: AquaFluid): Boolean {
        return state.material.replaceable || !state.material.solid
    }

    fun canSurvive(state: AquaBlockState, world: BlockGetter, pos: Vec3i): Boolean {
        return true
    }

    fun shouldSkipRendering(state: AquaBlockState, adjacent: AquaBlockState, face: Direction): Boolean {
        return false
    }

    fun getMaterialColor(state: AquaBlockState): MaterialColor {
        return MaterialColors.NONE
    }

    fun getLightEmission(state: AquaBlockState): Int {
        return 0
    }

    fun getOffsetType(state: AquaBlockState): BlockOffsetType {
        return BlockOffsetType.NONE
    }

    fun isValidSpawn(state: AquaBlockState, world: BlockGetter, pos: Vec3i, type: AquaEntityType<*>): Boolean {
        return state.isFaceSturdy(world, pos, Direction.UP) && getLightEmission(state) < 14
    }

    fun isRedstoneConductor(state: AquaBlockState, world: BlockGetter, pos: Vec3i): Boolean {
        return state.material.solidBlocking && state.isCollisionShapeFullBlock(world, pos)
    }

    fun hasPostProcess(state: AquaBlockState, world: BlockGetter, pos: Vec3i): Boolean {
        return false
    }

    fun isSuffocating(state: AquaBlockState, world: BlockGetter, pos: Vec3i): Boolean {
        return state.material.blocksMotion && state.isCollisionShapeFullBlock(world, pos)
    }

    fun hasGravity(): Boolean {
        return false
    }

    fun hasBlockEntity(): Boolean {
        return false
    }

    fun maximumHorizontalOffset(): Float {
        return 0.25F
    }

    fun maximumVerticalOffset(): Float {
        return 0.25F
    }

    fun randomlyTicks(state: AquaBlockState): Boolean {
        return false
    }

    fun propagatesSkylightDown(state: AquaBlockState, world: BlockGetter, pos: Vec3i): Boolean {
        return false
    }

    fun getPlacementState(context: BlockPlaceContext): AquaBlockState? {
        return null
    }
}
