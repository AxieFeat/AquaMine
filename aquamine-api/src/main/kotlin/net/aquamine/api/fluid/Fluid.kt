package net.aquamine.api.fluid

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed
import net.aquamine.api.block.Block
import net.aquamine.api.item.ItemType
import net.aquamine.api.state.StateHolder

/**
 * A fluid with certain properties.
 *
 * The design of this is very similar to that of the [Block].
 */
@CataloguedBy(Fluids::class)
@ImmutableType
interface Fluid : StateHolder<FluidState>, Keyed {

    /**
     * The type of the bucket this fluid can be held in.
     */
    val bucket: ItemType

    /**
     * The value of this fluid's resistance to explosions.
     */
    val explosionResistance: Double

    /**
     * If this fluid is an empty fluid.
     *
     * @return `true` if this fluid is empty.
     */
    fun isEmpty(): Boolean
}
