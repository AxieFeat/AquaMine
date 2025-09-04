package net.aquamine.generators.data.generators

import com.google.gson.JsonObject
import net.aquamine.generators.data.Generator
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

// TODO Fixme
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
            itemRegistry.getId(itemRegistry.get(it).get().value())
        }.forEach { resource ->
            val minecraftItem = itemRegistry.get(resource).get().value()

            val item = JsonObject()

            item.addProperty("rarity", "COMMON")
            item.addProperty("maxStackSize", minecraftItem.defaultMaxStackSize)
            item.addProperty("maxDamage", 0)
            item.addProperty("edible", false)
            item.addProperty("fireResistant", true)
            item.addProperty("eatingSound", "minecraft:entity.generic.eat")
            item.addProperty("drinkingSound", "minecraft:entity.generic.drink")

            items.add(resource.toString(), item)
        }

        return items
    }

}
