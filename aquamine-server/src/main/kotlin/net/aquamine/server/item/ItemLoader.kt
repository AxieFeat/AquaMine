package net.aquamine.server.item

import com.google.gson.JsonObject
import net.kyori.adventure.key.Key
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.item.ItemRarity
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.registry.KryptonRegistry
import net.aquamine.server.util.KryptonDataLoader

class ItemLoader(registry: KryptonRegistry<KryptonItemType>) : KryptonDataLoader<KryptonItemType>("items", registry) {

    override fun create(key: Key, value: JsonObject): KryptonItemType {
        val rarityName = value.get("rarity")?.asString ?: "COMMON"
        val rarity = ItemRarity.valueOf(rarityName)
        val maxStackSize = value.get("maxStackSize").asInt
        val maxDamage = value.get("maxDamage").asInt
        val edible = value.get("edible").asBoolean
        val fireResistant = value.get("fireResistant").asBoolean
        val eatingSound = KryptonRegistries.SOUND_EVENT.get(Key.key(value.get("eatingSound").asString)) ?: SoundEvents.GENERIC_EAT.get()
        val drinkingSound = KryptonRegistries.SOUND_EVENT.get(Key.key(value.get("drinkingSound").asString)) ?: SoundEvents.GENERIC_DRINK.get()
        return KryptonItemType(rarity, maxStackSize, maxDamage, edible, fireResistant, eatingSound, drinkingSound)
    }
}
