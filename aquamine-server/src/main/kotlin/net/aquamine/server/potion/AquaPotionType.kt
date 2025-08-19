package net.aquamine.server.potion

import net.aquamine.api.potion.PotionType
import net.aquamine.api.potion.PotionTypeCategory
import net.aquamine.api.util.Color
import net.aquamine.server.potion.PotionEffectHandler
import net.kyori.adventure.key.Key

@JvmRecord
data class AquaPotionType(
    private val key: Key,
    private val translationKey: String,
    override val category: PotionTypeCategory,
    override val color: Color,
    val handler: PotionEffectHandler
) : PotionType {

    override fun key(): Key = key
    override fun translationKey(): String = translationKey

}
