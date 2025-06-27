package net.aquamine.server.item

import com.google.gson.JsonObject
import net.kyori.adventure.key.Key
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.item.ItemRarity
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.registry.AquaRegistry
import net.aquamine.server.util.AquaDataLoader

class ItemLoader(registry: AquaRegistry<AquaItemType>) : AquaDataLoader<AquaItemType>("items", registry) {

    override fun create(key: Key, value: JsonObject): AquaItemType {
        val rarityName = value.get("rarity")?.asString ?: "COMMON"
        val rarity = ItemRarity.valueOf(rarityName)
        val maxStackSize = value.get("maxStackSize").asInt
        val maxDamage = value.get("maxDamage").asInt
        val edible = value.get("edible").asBoolean
        val fireResistant = value.get("fireResistant").asBoolean
        val eatingSound = AquaRegistries.SOUND_EVENT.get(Key.key(value.get("eatingSound").asString)) ?: SoundEvents.GENERIC_EAT.get()
        val drinkingSound = AquaRegistries.SOUND_EVENT.get(Key.key(value.get("drinkingSound").asString)) ?: SoundEvents.GENERIC_DRINK.get()
        return AquaItemType(rarity, maxStackSize, maxDamage, edible, fireResistant, eatingSound, drinkingSound)
    }
}
