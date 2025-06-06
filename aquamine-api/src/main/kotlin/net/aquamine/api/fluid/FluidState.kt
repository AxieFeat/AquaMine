package net.aquamine.api.fluid

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.block.BlockState
import net.aquamine.api.state.State

/**
 * A state of a fluid.
 */
@ImmutableType
interface FluidState : State<FluidState> {

    /**
     * The fluid this is a state of.
     */
    val fluid: Fluid

    /**
     * The level of this fluid state.
     *
     * Should be either a constant value, such as 0 for the empty fluid, or 8
     * for source fluids, or the value of the
     * [level][net.aquamine.api.state.Properties.LIQUID_LEVEL]
     * property for flowing fluids.
     */
    val level: Int

    /**
     * If this fluid state is empty.
     *
     * @return `true` if this fluid state is empty.
     */
    fun isEmpty(): Boolean = fluid.isEmpty()

    /**
     * If this fluid state is a source fluid.
     *
     * @return `true` if this fluid state is a source fluid.
     */
    fun isSource(): Boolean

    /**
     * Converts this fluid state in to its corresponding block state.
     *
     * @return This fluid state as a block state.
     */
    fun asBlock(): BlockState
}
