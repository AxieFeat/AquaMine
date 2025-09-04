package net.aquamine.generators.data.generators

import com.google.gson.JsonObject
import net.aquamine.generators.data.Generator
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.effect.MobEffects

object PotionTypeGenerator : Generator<MobEffects>() {

    override val names: Map<MobEffects, String> = MobEffects::class.java.getDeclaredFields().filter {
        MobEffects::class.java.isAssignableFrom(it.type)
    }.associate {
        val block = it.get(null) as MobEffects
        block to it.name
    }

    override fun generate(): JsonObject {
        val potions = JsonObject()

        val mobEffectRegistry = BuiltInRegistries.MOB_EFFECT

        mobEffectRegistry.keySet().sortedBy {
            mobEffectRegistry.getId(mobEffectRegistry.get(it).get().value())
        }.forEach { resource ->
            val minecraftPotion = mobEffectRegistry.get(resource).get().value()

            val potion = JsonObject()

            potion.addProperty("translationKey", minecraftPotion.descriptionId)
            potion.addProperty("category", minecraftPotion.category.name)
            potion.addProperty("color", minecraftPotion.color)

            potions.add(resource.toString(), potion)
        }

        return potions
    }

}
