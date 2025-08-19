package net.aquamine.server.potion

data class TimedPotionEffect(
    val potion: AquaPotionEffect,
    var ticksToEnd: Int = potion.duration,
)
