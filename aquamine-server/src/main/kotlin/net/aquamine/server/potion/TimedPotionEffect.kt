package net.aquamine.server.potion

@JvmRecord
data class TimedPotionEffect(
    val potion: AquaPotionEffect,
    val startingTime: Long = System.currentTimeMillis(),
)
