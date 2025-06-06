package net.aquamine.api.block.entity

/**
 * A campfire.
 */
interface Campfire : BlockEntity {

    /**
     * Gets the current progress of the item being cooked in the given [slot].
     *
     * @param slot The slot.
     *
     * @return The progress of the item in the slot.
     */
    fun getCookingProgress(slot: Int): Int

    /**
     * Gets the amount of time, in ticks, until the item in the given [slot]
     * is cooked.
     *
     * @param slot The slot.
     *
     * @return The amount of time until the item in the slot is cooked.
     */
    fun getCookingDuration(slot: Int): Int
}
