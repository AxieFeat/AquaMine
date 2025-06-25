package net.aquamine.server.world.components

import net.aquamine.server.world.dimension.KryptonDimensionType

interface WorldTimeAccessor : ReadOnlyWorld {

    val dayTime: Long

    fun moonPhase(): Int = dimensionType.moonPhase(dayTime)

    fun moonBrightness(): Float = KryptonDimensionType.MOON_BRIGHTNESS_PER_PHASE[moonPhase()]

    fun timeOfDay(value: Float): Float = dimensionType.timeOfDay(dayTime)
}
