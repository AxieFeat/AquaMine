package net.aquamine.api.entity.projectile

import net.aquamine.api.item.ItemStack

/**
 * A thrown trident.
 */
interface Trident : ArrowLike {

    /**
     * The item that is given to entities that pick up this trident.
     *
     * Defaults to a single stack with
     * [ItemTypes.TRIDENT].
     */
    val item: ItemStack

    /**
     * If this trident has already damaged an entity, in which case later
     * collisions with entities will deal no damage.
     */
    var dealtDamage: Boolean

    /**
     * The level of the loyalty enchantment on this trident.
     */
    var loyaltyLevel: Int

    /**
     * If this trident has the enchantment glint (is enchanted).
     */
    var isEnchanted: Boolean
}
