package net.aquamine.server.world.fluid

import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.server.shapes.Shapes
import net.aquamine.server.shapes.VoxelShape
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.components.BlockGetter

class EmptyFluid : AquaFluid() {

    override val bucket: ItemType
        get() = ItemTypes.AIR.get()
    override val explosionResistance: Double
        get() = 0.0

    override fun isEmpty(): Boolean = true

    override fun getFlow(world: BlockGetter, pos: Vec3i, state: AquaFluidState): Vec3d = Vec3d.ZERO

    override fun getHeight(state: AquaFluidState, world: BlockGetter, pos: Vec3i): Float = 0F

    override fun getOwnHeight(state: AquaFluidState): Float = 0F

    override fun asBlock(state: AquaFluidState): AquaBlockState = AquaBlocks.AIR.defaultState

    override fun isSource(state: AquaFluidState): Boolean = false

    override fun level(state: AquaFluidState): Int = 0

    override fun getShape(state: AquaFluidState, world: BlockGetter, pos: Vec3i): VoxelShape = Shapes.empty()
}
