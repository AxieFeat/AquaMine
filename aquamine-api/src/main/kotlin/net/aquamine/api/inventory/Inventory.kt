package net.aquamine.api.inventory

import net.aquamine.api.item.ItemStack

/**
 * An inventory that contains items.
 *
 * The absence of a value in an inventory will always be represented by
 * [ItemStack.empty], not null. This includes the default values in lists of
 * items, such as [items].
 */
interface Inventory : Iterable<ItemStack> {

    /**
     * The size of this inventory.
     */
    val size: Int

    /**
     * The type of this inventory.
     */
    val type: InventoryType

    /**
     * The items in this inventory.
     */
    val items: List<ItemStack>

    /**
     * If this inventory contains the specified [item].
     *
     * @param item The item.
     *
     * @return `true` if the [item] is in this inventory, `false` otherwise.
     */
    fun hasItem(item: ItemStack): Boolean

    /**
     * Retrieve an item from this inventory at the specified [index].
     *
     * @param index the index (slot, starts from 0) of the item to retrieve.
     *
     * @return The item at that slot, or [ItemStack.empty] if there is no item
     * at that slot.
     *
     * @throws ArrayIndexOutOfBoundsException If [index] is out of bounds
     * (not in 0 <= [index] < [size]).
     */
    fun getItem(index: Int): ItemStack

    /**
     * Sets the item at the given [index] to the given [item].
     *
     * @param index The index of the item.
     * @param item The new item.
     */
    fun setItem(index: Int, item: ItemStack)

    /**
     * Puts the specified item in the first available empty slot in this
     * inventory, or does nothing if the inventory is full.
     *
     * @param item The item to add.
     */
    fun addItem(item: ItemStack)

    /**
     * Removes the specified [item] from the array, or does nothing if there
     * isn't an element that matches this [item].
     *
     * @param item The item to remove.
     */
    fun removeItem(item: ItemStack)

    /**
     * Clears this inventory.
     */
    fun clear()
}
