package net.aquamine.api.inventory

import net.aquamine.api.entity.ArmorSlot
import net.aquamine.api.entity.Hand
import net.aquamine.api.entity.player.Player
import net.aquamine.api.item.ItemStack

/**
 * Represents a player's inventory.
 */
interface PlayerInventory : Inventory {

    /**
     * The main inventory area, excluding the hotbar area. This has 27 slots
     * and covers from slots 9 to 35.
     */
    val main: List<ItemStack>

    /**
     * The hotbar area. This has 9 slots and covers from slots 0 to 8.
     */
    val hotbar: List<ItemStack>

    /**
     * The array of crafting slots in this inventory, where the first 4
     * elements of this array are the input, and the last slot is the output.
     *
     * The returned list is immutable and of a fixed size.
     */
    val crafting: List<ItemStack>

    /**
     * The array of armor pieces in this inventory.
     *
     * The returned list is immutable and of a fixed size.
     */
    val armor: List<ItemStack>

    /**
     * The item that this player is currently holding in their main hand.
     */
    val mainHand: ItemStack

    /**
     * The item that this player is currently holding in their offhand.
     */
    val offHand: ItemStack

    /**
     * The helmet this player is currently wearing.
     */
    var helmet: ItemStack

    /**
     * The chestplate this player is currently wearing.
     */
    var chestplate: ItemStack

    /**
     * The leggings this player is currently wearing.
     */
    var leggings: ItemStack

    /**
     * The boots this player is currently wearing.
     */
    var boots: ItemStack

    /**
     * The slot of the currently held item.
     */
    val heldSlot: Int

    /**
     * The owner of this player inventory.
     */
    val owner: Player

    /**
     * Gets the armor item in the given [slot].
     *
     * @param slot The armor slot.
     *
     * @return The armor item in the given [slot].
     */
    fun getArmor(slot: ArmorSlot): ItemStack

    /**
     * Sets the armor item in the given [slot] to the give [item].
     *
     * @param slot The armor slot.
     * @param item The new item.
     */
    fun setArmor(slot: ArmorSlot, item: ItemStack)

    /**
     * Gets the item the player is holding in the given [hand].
     *
     * @param hand The hand.
     *
     * @return The item.
     */
    fun getHeldItem(hand: Hand): ItemStack

    /**
     * Sets the item the player is holding in the given [hand] to the given
     * [item].
     *
     * @param hand The hand.
     *
     * @param item The item.
     */
    fun setHeldItem(hand: Hand, item: ItemStack)
}
