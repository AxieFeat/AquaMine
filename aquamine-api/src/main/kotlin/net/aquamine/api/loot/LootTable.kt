package net.aquamine.api.loot

import net.aquamine.api.item.ItemStack

/**
 * Represents generable loot table.
 */
interface LootTable {

    /**
     * Generate new items from this table.
     *
     * @param seed Seed of random for generating.
     * @param context Context for loot table.
     *
     * @return Collection of generated [ItemStack]'s.
     */
    fun generate(seed: Long, context: LootContext): Collection<ItemStack>
}
