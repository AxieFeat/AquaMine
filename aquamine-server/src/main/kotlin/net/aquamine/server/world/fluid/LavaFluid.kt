package net.aquamine.server.world.fluid

import net.aquamine.api.fluid.Fluid
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.state.StateDefinition
import net.aquamine.server.world.block.KryptonBlocks
import net.aquamine.server.world.block.state.KryptonBlockState

abstract class LavaFluid : FlowingFluid() {

    override val bucket: ItemType
        get() = ItemTypes.LAVA_BUCKET.get()
    override val explosionResistance: Double
        get() = 100.0

    override fun flowing(): KryptonFluid = KryptonFluids.FLOWING_LAVA

    override fun source(): KryptonFluid = KryptonFluids.LAVA

    override fun isSame(fluid: Fluid): Boolean = fluid === source() || fluid === flowing()

    override fun asBlock(state: KryptonFluidState): KryptonBlockState = KryptonBlocks.LAVA.defaultState.setProperty(LEVEL, calculateBlockLevel(state))

    class Flowing : LavaFluid() {

        override fun createStateDefinition(builder: StateDefinition.Builder<KryptonFluid, KryptonFluidState>) {
            super.createStateDefinition(builder)
            builder.add(LEVEL)
        }

        override fun level(state: KryptonFluidState): Int = state.requireProperty(LEVEL)

        override fun isSource(state: KryptonFluidState): Boolean = false
    }

    class Source : LavaFluid() {

        override fun level(state: KryptonFluidState): Int = MAX_LEVEL

        override fun isSource(state: KryptonFluidState): Boolean = false
    }
}
