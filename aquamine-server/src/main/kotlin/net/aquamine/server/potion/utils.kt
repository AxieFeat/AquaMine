package net.aquamine.server.potion

import net.aquamine.api.potion.PotionEffect
import net.aquamine.api.potion.PotionType
import net.aquamine.server.util.downcastApiType

fun PotionEffect.downcast(): AquaPotionEffect = downcastApiType("PotionEffect")

fun PotionType.downcast(): AquaPotionType = downcastApiType("PotionType")
