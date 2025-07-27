package net.aquamine.api.loot

import net.aquamine.api.item.ItemStack
import kotlin.random.Random

interface LootTable {

    fun generate(random: Random, context: LootContext): Collection<ItemStack>

}