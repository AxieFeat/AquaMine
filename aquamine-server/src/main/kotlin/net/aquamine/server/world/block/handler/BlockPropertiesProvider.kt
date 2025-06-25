package net.aquamine.server.world.block.handler

import net.aquamine.api.block.PushReaction
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.item.context.BlockPlaceContext
import net.aquamine.server.world.block.data.BlockOffsetType
import net.aquamine.server.world.block.data.RenderShape
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.components.BlockGetter
import net.aquamine.server.world.fluid.KryptonFluid
import net.aquamine.server.world.fluid.KryptonFluidState
import net.aquamine.server.world.fluid.KryptonFluids
import net.aquamine.server.world.material.MaterialColor
import net.aquamine.server.world.material.MaterialColors

interface BlockPropertiesProvider {

    fun isApplicableBlockType(name: String): Boolean

    fun getRenderShape(state: KryptonBlockState): RenderShape {
        return RenderShape.MODEL
    }

    fun getPushReaction(state: KryptonBlockState): PushReaction {
        return state.material.pushReaction
    }

    fun getFluidState(state: KryptonBlockState): KryptonFluidState = KryptonFluids.EMPTY.defaultState

    fun getItemStack(world: BlockGetter, pos: Vec3i, state: KryptonBlockState): KryptonItemStack {
        return KryptonItemStack.EMPTY
    }

    fun useShapeForLightOcclusion(state: KryptonBlockState): Boolean {
        return false
    }

    fun getLightBlock(state: KryptonBlockState, world: BlockGetter, pos: Vec3i): Int {
        if (state.isSolidRender(world, pos)) return world.maximumLightLevel()
        return if (state.propagatesSkylightDown(world, pos)) 0 else 1
    }

    fun canBeReplaced(state: KryptonBlockState, context: BlockPlaceContext): Boolean {
        return state.material.replaceable && (context.item.isEmpty() || !context.item.eq(state.block.asItem()))
    }

    fun canBeReplaced(state: KryptonBlockState, fluid: KryptonFluid): Boolean {
        return state.material.replaceable || !state.material.solid
    }

    fun canSurvive(state: KryptonBlockState, world: BlockGetter, pos: Vec3i): Boolean {
        return true
    }

    fun shouldSkipRendering(state: KryptonBlockState, adjacent: KryptonBlockState, face: Direction): Boolean {
        return false
    }

    fun getMaterialColor(state: KryptonBlockState): MaterialColor {
        return MaterialColors.NONE
    }

    fun getLightEmission(state: KryptonBlockState): Int {
        return 0
    }

    fun getOffsetType(state: KryptonBlockState): BlockOffsetType {
        return BlockOffsetType.NONE
    }

    fun isValidSpawn(state: KryptonBlockState, world: BlockGetter, pos: Vec3i, type: KryptonEntityType<*>): Boolean {
        return state.isFaceSturdy(world, pos, Direction.UP) && getLightEmission(state) < 14
    }

    fun isRedstoneConductor(state: KryptonBlockState, world: BlockGetter, pos: Vec3i): Boolean {
        return state.material.solidBlocking && state.isCollisionShapeFullBlock(world, pos)
    }

    fun hasPostProcess(state: KryptonBlockState, world: BlockGetter, pos: Vec3i): Boolean {
        return false
    }

    fun isSuffocating(state: KryptonBlockState, world: BlockGetter, pos: Vec3i): Boolean {
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

    fun randomlyTicks(state: KryptonBlockState): Boolean {
        return false
    }

    fun propagatesSkylightDown(state: KryptonBlockState, world: BlockGetter, pos: Vec3i): Boolean {
        return false
    }

    fun getPlacementState(context: BlockPlaceContext): KryptonBlockState? {
        return null
    }
}
