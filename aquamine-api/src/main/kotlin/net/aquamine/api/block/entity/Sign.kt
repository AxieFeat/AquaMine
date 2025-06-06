package net.aquamine.api.block.entity

import net.kyori.adventure.text.Component
import net.aquamine.api.item.data.DyeColor

/**
 * A sign.
 */
interface Sign : BlockEntity {

    /**
     * The lines of text on this sign.
     */
    val lines: Collection<Component>

    /**
     * Whether this sign is editable.
     */
    var isEditable: Boolean

    /**
     * Whether this sign has text that is glowing.
     */
    var isTextGlowing: Boolean

    /**
     * The color that this sign is dyed.
     */
    var color: DyeColor

    /**
     * Gets the line of text on this sign at the given [index].
     *
     * @param index the index
     * @return the line
     */
    fun getLine(index: Int): Component

    /**
     * Sets the line of text on this sign at the given [index] to the given
     * [line].
     *
     * @param index the index
     * @param line the line
     */
    fun setLine(index: Int, line: Component)
}
