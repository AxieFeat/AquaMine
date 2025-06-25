package net.aquamine.server.world.biome

import net.aquamine.api.util.Color
import net.aquamine.api.world.biome.Precipitation
import net.aquamine.api.world.biome.TemperatureModifier
import net.aquamine.server.world.biome.data.KryptonAmbientMoodSettings
import net.aquamine.server.world.biome.data.KryptonClimate

object EndBiomes {

    private const val TEMPERATURE = 0.5F
    private const val DOWNFALL = 0.5F
    private val FOG = Color(160, 128, 160)

    @JvmStatic
    fun endBarrens(): KryptonBiome = baseEnd()

    @JvmStatic
    fun theEnd(): KryptonBiome = baseEnd()

    @JvmStatic
    fun endMidlands(): KryptonBiome = baseEnd()

    @JvmStatic
    fun endHighlands(): KryptonBiome = baseEnd()

    @JvmStatic
    fun smallEndIslands(): KryptonBiome = baseEnd()

    @JvmStatic
    private fun baseEnd(): KryptonBiome = KryptonBiome.Builder().apply {
        climate(KryptonClimate(Precipitation.NONE, TEMPERATURE, DOWNFALL, TemperatureModifier.NONE))
        effects {
            waterColor(OverworldBiomes.OVERWORLD_WATER)
            waterFogColor(OverworldBiomes.OVERWORLD_WATER_FOG)
            fogColor(FOG)
            skyColor(Color.BLACK)
            mood(KryptonAmbientMoodSettings.CAVE)
        }
    }.build()
}
