package net.aquamine.api.loot

/**
 * Represents context for loot tables.
 */
interface LootContext {

    /**
     * Luck modifier, example from [net.aquamine.api.potion.PotionTypes.LUCK] effect.
     */
    val luck: Float

    /**
     * Modifier from enchantment.
     */
    val lootingModifier: Int

}
