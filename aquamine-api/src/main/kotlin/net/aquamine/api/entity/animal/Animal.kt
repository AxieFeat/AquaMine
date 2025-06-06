package net.aquamine.api.entity.animal

import net.aquamine.api.entity.Ageable
import net.aquamine.api.item.ItemStack
import java.util.UUID

/**
 * A creature that can fall in love with others of its kind, breed, and
 * produce offspring.
 *
 * The love mechanic can be a bit confusing, but what it actually means for an
 * animal to be "in love" is that they are looking for a mate. For most
 * animals, a player feeding them will trigger this effect.
 *
 * The animal will remain in love until either it successfully finds a mate,
 * breeds, and produces offspring, or the timer runs out.
 */
interface Animal : Ageable {

    /**
     * The time remaining that this animal will be in love for.
     */
    val inLoveTime: Int

    /**
     * The cause of this animal being in love if it is currently in love.
     *
     * If it is not in love, this will always be null.
     */
    var loveCause: UUID?

    /**
     * If this animal can fall in love.
     *
     * @return true if this animal can fall in love
     */
    fun canFallInLove(): Boolean

    /**
     * Whether this animal is currently in love, meaning it is looking for a
     * mate.
     *
     * @return true if this animal is in love
     */
    fun isInLove(): Boolean

    /**
     * Returns true if this animal is eligible to mate with the given [target]
     * animal, or false otherwise.
     *
     * @param target the target
     * @return true if this animal can mate with the target, false otherwise
     */
    fun canMate(target: Animal): Boolean

    /**
     * Returns true if the given [item] is considered food for this animal, or
     * false otherwise.
     *
     * @param item the item
     * @return true if the item is food, false otherwise
     */
    fun isFood(item: ItemStack): Boolean
}
