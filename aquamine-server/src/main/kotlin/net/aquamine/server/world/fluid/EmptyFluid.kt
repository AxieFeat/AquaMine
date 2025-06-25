package net.aquamine.server.world.fluid

import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.server.shapes.Shapes
import net.aquamine.server.shapes.VoxelShape
import net.aquamine.server.world.block.KryptonBlocks
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.components.BlockGetter

class EmptyFluid : KryptonFluid() {

    override val bucket: ItemType
        get() = ItemTypes.AIR.get()
    override val explosionResistance: Double
        get() = 0.0

    override fun isEmpty(): Boolean = true

    override fun getFlow(world: BlockGetter, pos: Vec3i, state: KryptonFluidState): Vec3d = Vec3d.ZERO

    override fun getHeight(state: KryptonFluidState, world: BlockGetter, pos: Vec3i): Float = 0F

    override fun getOwnHeight(state: KryptonFluidState): Float = 0F

    override fun asBlock(state: KryptonFluidState): KryptonBlockState = KryptonBlocks.AIR.defaultState

    override fun isSource(state: KryptonFluidState): Boolean = false

    override fun level(state: KryptonFluidState): Int = 0

    override fun getShape(state: KryptonFluidState, world: BlockGetter, pos: Vec3i): VoxelShape = Shapes.empty()
}
