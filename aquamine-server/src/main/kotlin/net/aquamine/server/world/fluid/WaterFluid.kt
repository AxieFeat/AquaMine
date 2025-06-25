package net.aquamine.server.world.fluid

import net.aquamine.api.fluid.Fluid
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.state.StateDefinition
import net.aquamine.server.world.block.KryptonBlocks
import net.aquamine.server.world.block.state.KryptonBlockState

abstract class WaterFluid : FlowingFluid() {

    override val bucket: ItemType
        get() = ItemTypes.WATER_BUCKET.get()
    override val explosionResistance: Double
        get() = 100.0

    override fun flowing(): KryptonFluid = KryptonFluids.FLOWING_WATER

    override fun source(): KryptonFluid = KryptonFluids.WATER

    override fun isSame(fluid: Fluid): Boolean = fluid === source() || fluid === flowing()

    override fun asBlock(state: KryptonFluidState): KryptonBlockState =
        KryptonBlocks.WATER.defaultState.setProperty(LEVEL, calculateBlockLevel(state))

    class Flowing : WaterFluid() {

        override fun createStateDefinition(builder: StateDefinition.Builder<KryptonFluid, KryptonFluidState>) {
            super.createStateDefinition(builder)
            builder.add(LEVEL)
        }

        override fun level(state: KryptonFluidState): Int = state.requireProperty(LEVEL)

        override fun isSource(state: KryptonFluidState): Boolean = false
    }

    class Source : WaterFluid() {

        override fun level(state: KryptonFluidState): Int = MAX_LEVEL

        override fun isSource(state: KryptonFluidState): Boolean = true
    }
}
