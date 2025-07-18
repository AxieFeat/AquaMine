package net.aquamine.generators.data.generators

import com.google.gson.JsonObject
import net.aquamine.generators.data.Generator
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

object ItemGenerator : Generator<Item>() {

    override val names: Map<Item, String> = Items::class.java.getDeclaredFields().filter {
        Item::class.java.isAssignableFrom(it.type)
    }.associate {
            val item = it.get(null) as Item
            item to it.name
    }

    override fun generate(): JsonObject {
        val items = JsonObject()

        val itemRegistry = BuiltInRegistries.ITEM
        val soundEventRegistry = BuiltInRegistries.SOUND_EVENT

        itemRegistry.keySet().sortedBy {
            itemRegistry.getId(itemRegistry.get(it))
        }.forEach { resource ->
            val minecraftItem = itemRegistry.get(resource)

            val item = JsonObject()

            item.addProperty("rarity", minecraftItem.getRarity(ItemStack.EMPTY).name)
            item.addProperty("maxStackSize", minecraftItem.maxStackSize)
            item.addProperty("maxDamage", minecraftItem.maxDamage)
            item.addProperty("edible", minecraftItem.isEdible)
            item.addProperty("fireResistant", minecraftItem.isFireResistant)
            soundEventRegistry.getKey(minecraftItem.eatingSound).let {
                item.addProperty("eatingSound", it.toString())
            }
            soundEventRegistry.getKey(minecraftItem.drinkingSound).let {
                item.addProperty("drinkingSound", it.toString())
            }

            items.add(resource.toString(), item)
        }

        return items
    }

}
