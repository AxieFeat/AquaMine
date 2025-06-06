package net.aquamine.api.entity.player

import net.aquamine.api.item.ItemType

/**
 * The tracker of item cooldowns for an object.
 */
interface CooldownTracker {

    /**
     * Checks if the given [item] is currently on cooldown.
     *
     * @param item The item.
     *
     * @return `true` if the item is currently on cooldown, `false` otherwise.
     */
    fun hasCooldown(item: ItemType): Boolean

    /**
     * Gets the current cooldown for the given [item], or returns `-1` if there
     * is no cooldown for the given [item].
     *
     * @param item The item.
     *
     * @return The current cooldown for the item, or -1 if not present.
     */
    fun getCooldown(item: ItemType): Int

    /**
     * Gets the current cooldown percentage for the given [item].
     *
     * @param item The item.
     *
     * @return The percentage.
     */
    fun getCooldownPercentage(item: ItemType): Float

    /**
     * Sets the cooldown for the given [item] to the given amount of [ticks].
     *
     * @param item The item.
     *
     * @param ticks The number of ticks the cooldown will last for.
     */
    fun setCooldown(item: ItemType, ticks: Int)

    /**
     * Resets the cooldown for the given [item].
     *
     * @param item The item.
     */
    fun resetCooldown(item: ItemType)
}
