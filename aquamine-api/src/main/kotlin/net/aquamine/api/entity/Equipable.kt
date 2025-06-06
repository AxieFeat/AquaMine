package net.aquamine.api.entity

import net.aquamine.api.item.ItemStack

/**
 * Something that can be equipped with hand and armor items.
 */
interface Equipable {

    /**
     * Gets the item that this equipable holds in the given [hand].
     *
     * This may return [ItemStack.empty] if this equipable is not holding an item in
     * the given [hand].
     *
     * @param hand The hand.
     *
     * @return The item this mob is holding in the hand.
     */
    fun getHeldItem(hand: Hand): ItemStack

    /**
     * Sets the item that this mob holds in the given [hand] to the given
     * [item].
     *
     * @param hand The hand.
     * @param item The item.
     */
    fun setHeldItem(hand: Hand, item: ItemStack)

    /**
     * Gets the armor item that this mob has equipped in the given [slot].
     *
     * This may return [ItemStack.empty] if this mob does not have any armor
     * equipped in the given [slot].
     *
     * @param slot The slot.
     */
    fun getArmor(slot: ArmorSlot): ItemStack

    /**
     * Sets the armor item that this mob has equipped in the given [slot] to
     * the given [item].
     *
     * @param slot The slot.
     * @param item The item.
     */
    fun setArmor(slot: ArmorSlot, item: ItemStack)

    /**
     * Gets the equipment item that this mob has equipped in the given [slot].
     *
     * This may return [ItemStack.empty] if this mob does not have any armor
     * equipped in the given [slot].
     *
     * @param slot The slot.
     *
     * @return The item in the given slot.
     */
    fun getEquipment(slot: EquipmentSlot): ItemStack

    /**
     * Sets the equipment item that this mob has equipped in the given [slot] to
     * the given [item].
     *
     * @param slot The slot.
     * @param item The item.
     */
    fun setEquipment(slot: EquipmentSlot, item: ItemStack)
}
