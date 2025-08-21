package net.aquamine.server.potion

class TimedPotionEffect(
    val potion: AquaPotionEffect,
    var ticksToEnd: Int = potion.duration,
)
