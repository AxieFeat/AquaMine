package net.aquamine.api.block

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.fluid.FluidState
import net.aquamine.api.state.State

/**
 * A state that a block may be in.
 *
 * This is a representation of a block with specific properties that affect its
 * behavior.
 */
@ImmutableType
interface BlockState : State<BlockState> {

    /**
     * The block this is a state of.
     */
    val block: Block

    /**
     * The hardness of this block state.
     *
     * This is used in determining the mining time of this block when a player
     * attempts to mine it.
     */
    val hardness: Double

    /**
     * How this block reacts to being pushed or pulled by pistons.
     */
    val pushReaction: PushReaction

    /**
     * If this block state is air.
     *
     * @return `true` if this block state is air.
     */
    fun isAir(): Boolean

    /**
     * If this block state is a solid object with collision, meaning,
     * entities cannot pass through it.
     *
     * @return `true` if this block state is solid.
     */
    fun isSolid(): Boolean

    /**
     * If this block state represents a liquid rather than a solid object.
     *
     * All block states that are liquids are also fluid states.
     *
     * @return `true` if this block state is a liquid.
     */
    fun isLiquid(): Boolean

    /**
     * If this block state can be set on fire.
     *
     * @return `true` if this block state is flammable.
     */
    fun isFlammable(): Boolean

    /**
     * If this block state can be replaced with another block state
     * when attempting to place a block on this block.
     *
     * For example, when a player places a block on grass, instead of the
     * block being placed on the side in which the player clicked, the target
     * grass will be replaced with the block the player was placing.
     *
     * @return `true` if this block state is replaceable.
     */
    fun isReplaceable(): Boolean

    /**
     * If this block state is opaque, meaning no light can pass through it.
     *
     * @return `true` if this block state is opaque.
     */
    fun isOpaque(): Boolean

    /**
     * If this block can be moved through.
     *
     * @return `true` if this block state blocks motion.
     */
    fun blocksMotion(): Boolean

    /**
     * Converts this block state in to its corresponding fluid state.
     *
     * If this block state is not a liquid, this will return the default state
     * of Fluids.EMPTY.
     *
     * @return this block state as a fluid state.
     */
    fun asFluid(): FluidState
}
