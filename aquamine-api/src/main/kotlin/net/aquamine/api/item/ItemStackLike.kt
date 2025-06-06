package net.aquamine.api.item

/**
 * Something that can be represented by an item stack.
 */
fun interface ItemStackLike {

    /**
     * Converts this object to its item stack representation.
     *
     * @return The item stack representation.
     */
    fun asItem(): ItemStack
}
