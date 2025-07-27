package net.aquamine.api.loot

import net.aquamine.api.item.ItemStack
import kotlin.random.Random

/**
 * Represents generable loot table.
 */
interface LootTable {

    /**
     * Generate new items from this table.
     *
     * @param random Random for items.
     * @param context Context for loot table.
     *
     * @return Collection of generated [ItemStack]'s.
     */
    fun generate(random: Random, context: LootContext): Collection<ItemStack>

}
