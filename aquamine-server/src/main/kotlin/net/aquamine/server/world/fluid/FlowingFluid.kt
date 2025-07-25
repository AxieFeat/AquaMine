package net.aquamine.server.world.fluid

import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.shapes.Shapes
import net.aquamine.server.shapes.VoxelShape
import net.aquamine.server.state.StateDefinition
import net.aquamine.server.state.property.AquaProperties
import net.aquamine.server.state.property.AquaProperty
import net.aquamine.server.util.enumhelper.Directions
import net.aquamine.server.world.components.BlockGetter
import net.aquamine.server.world.material.Materials
import kotlin.math.min

abstract class FlowingFluid : AquaFluid() {

    private val shapes = HashMap<AquaFluidState, VoxelShape>()

    abstract fun flowing(): AquaFluid

    abstract fun source(): AquaFluid

    fun getFlowing(level: Int, falling: Boolean): AquaFluidState = flowing().defaultState.setProperty(LEVEL, level).setProperty(FALLING, falling)

    fun getSource(falling: Boolean): AquaFluidState = source().defaultState.setProperty(FALLING, falling)

    override fun createStateDefinition(builder: StateDefinition.Builder<AquaFluid, AquaFluidState>) {
        builder.add(FALLING)
    }

    override fun getFlow(world: BlockGetter, pos: Vec3i, state: AquaFluidState): Vec3d {
        var flowX = 0.0
        var flowZ = 0.0

        Directions.Plane.HORIZONTAL.forEach {
            val offsetPos = Vec3i(pos.x + it.normalX, pos.y + it.normalY, pos.z + it.normalZ)
            val fluid = world.getFluid(offsetPos)
            if (!affectsFlow(fluid)) return@forEach

            var ownHeight = fluid.ownHeight()
            var height = 0F
            if (ownHeight == 0F) {
                if (!world.getBlock(offsetPos).material.blocksMotion) {
                    val below = world.getFluid(offsetPos.relative(Direction.DOWN))
                    if (affectsFlow(below)) {
                        ownHeight = below.ownHeight()
                        if (ownHeight > 0F) height = state.ownHeight() - (ownHeight - OWN_HEIGHT_OFFSET)
                    }
                }
            } else if (ownHeight > 0F) {
                height = state.ownHeight() - ownHeight
            }
            if (height != 0F) {
                flowX += (it.normalX * height).toDouble()
                flowZ += (it.normalZ * height).toDouble()
            }
        }

        var flow = Vec3d(flowX, 0.0, flowZ)
        if (state.requireProperty(FALLING)) {
            for (direction in Directions.Plane.HORIZONTAL.iterator()) {
                val offsetPos = Vec3i(pos.x + direction.normalX, pos.y + direction.normalY, pos.z + direction.normalZ)
                if (isSolidFace(world, offsetPos, direction) || isSolidFace(world, offsetPos.relative(Direction.UP), direction)) {
                    flow = flow.normalize().add(0.0, -6.0, 0.0)
                    break
                }
            }
        }
        return flow.normalize()
    }

    override fun getHeight(state: AquaFluidState, world: BlockGetter, pos: Vec3i): Float =
        if (hasSameAbove(state, world, pos)) 1F else state.ownHeight()

    override fun getOwnHeight(state: AquaFluidState): Float = state.level / 9F

    override fun getShape(state: AquaFluidState, world: BlockGetter, pos: Vec3i): VoxelShape {
        if (state.level == 9 && hasSameAbove(state, world, pos)) return Shapes.block()
        return shapes.computeIfAbsent(state) { Shapes.box(0.0, 0.0, 0.0, 1.0, it.getHeight(world, pos).toDouble(), 1.0) }
    }

    private fun affectsFlow(state: AquaFluidState): Boolean = state.isEmpty() || state.fluid.isSame(this)

    protected fun isSolidFace(world: BlockGetter, pos: Vec3i, side: Direction): Boolean {
        val block = world.getBlock(pos)
        val fluid = world.getFluid(pos)
        if (fluid.fluid.isSame(this)) return false
        if (side == Direction.UP) return true
        if (block.material == Materials.ICE) return false
        return block.isFaceSturdy(world, pos, side)
    }

    companion object {

        private const val OWN_HEIGHT_OFFSET = 1F - AquaEntity.BREATHING_DISTANCE_BELOW_EYES
        const val MAX_LEVEL: Int = 8
        @JvmField
        val LEVEL: AquaProperty<Int> = AquaProperties.LIQUID_LEVEL
        @JvmField
        val FALLING: AquaProperty<Boolean> = AquaProperties.FALLING

        @JvmStatic
        protected fun calculateBlockLevel(state: AquaFluidState): Int {
            if (state.isSource()) return 0
            val fallingBonus = if (state.requireProperty(FALLING)) MAX_LEVEL else 0
            return MAX_LEVEL - min(state.level, MAX_LEVEL) + fallingBonus
        }

        @JvmStatic
        private fun hasSameAbove(state: AquaFluidState, world: BlockGetter, pos: Vec3i): Boolean =
            state.fluid.isSame(world.getFluid(pos.relative(Direction.UP)).fluid)
    }
}
