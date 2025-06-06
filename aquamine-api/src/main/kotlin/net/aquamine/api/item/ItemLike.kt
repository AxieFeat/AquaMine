package net.aquamine.api.item

/**
 * Something that can be represented by an item.
 */
fun interface ItemLike {

    /**
     * Converts this object to its item representation.
     *
     * @return The item representation.
     */
    fun asItem(): ItemType
}
