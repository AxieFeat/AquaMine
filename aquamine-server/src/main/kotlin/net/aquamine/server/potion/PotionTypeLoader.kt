package net.aquamine.server.potion

import com.google.gson.JsonObject
import net.aquamine.api.potion.PotionTypeCategory
import net.aquamine.api.util.Color
import net.aquamine.server.registry.AquaRegistry
import net.aquamine.server.util.AquaDataLoader
import net.kyori.adventure.key.Key

class PotionTypeLoader(registry: AquaRegistry<AquaPotionType>) : AquaDataLoader<AquaPotionType>("potion_types", registry) {

    override fun create(key: Key, value: JsonObject): AquaPotionType {
        val translationKey = value.get("translationKey").asString
        val category = value.get("category").asString
        val color = value.get("color").asInt

        return AquaPotionType(
            key,
            translationKey,
            PotionTypeCategory.from(category)!!,
            Color(color),
        )
    }
}
