package net.aquamine.server.world.fluid

import net.aquamine.api.fluid.Fluid
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.state.StateDefinition
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.state.AquaBlockState

abstract class LavaFluid : FlowingFluid() {

    override val bucket: ItemType
        get() = ItemTypes.LAVA_BUCKET.get()
    override val explosionResistance: Double
        get() = 100.0

    override fun flowing(): AquaFluid = AquaFluids.FLOWING_LAVA

    override fun source(): AquaFluid = AquaFluids.LAVA

    override fun isSame(fluid: Fluid): Boolean = fluid === source() || fluid === flowing()

    override fun asBlock(state: AquaFluidState): AquaBlockState = AquaBlocks.LAVA.defaultState.setProperty(LEVEL, calculateBlockLevel(state))

    class Flowing : LavaFluid() {

        override fun createStateDefinition(builder: StateDefinition.Builder<AquaFluid, AquaFluidState>) {
            super.createStateDefinition(builder)
            builder.add(LEVEL)
        }

        override fun level(state: AquaFluidState): Int = state.requireProperty(LEVEL)

        override fun isSource(state: AquaFluidState): Boolean = false
    }

    class Source : LavaFluid() {

        override fun level(state: AquaFluidState): Int = MAX_LEVEL

        override fun isSource(state: AquaFluidState): Boolean = false
    }
}
