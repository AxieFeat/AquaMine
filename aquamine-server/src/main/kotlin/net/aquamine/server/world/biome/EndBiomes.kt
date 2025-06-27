package net.aquamine.server.world.biome

import net.aquamine.api.util.Color
import net.aquamine.api.world.biome.Precipitation
import net.aquamine.api.world.biome.TemperatureModifier
import net.aquamine.server.world.biome.data.AquaAmbientMoodSettings
import net.aquamine.server.world.biome.data.AquaClimate

object EndBiomes {

    private const val TEMPERATURE = 0.5F
    private const val DOWNFALL = 0.5F
    private val FOG = Color(160, 128, 160)

    @JvmStatic
    fun endBarrens(): AquaBiome = baseEnd()

    @JvmStatic
    fun theEnd(): AquaBiome = baseEnd()

    @JvmStatic
    fun endMidlands(): AquaBiome = baseEnd()

    @JvmStatic
    fun endHighlands(): AquaBiome = baseEnd()

    @JvmStatic
    fun smallEndIslands(): AquaBiome = baseEnd()

    @JvmStatic
    private fun baseEnd(): AquaBiome = AquaBiome.Builder().apply {
        climate(AquaClimate(Precipitation.NONE, TEMPERATURE, DOWNFALL, TemperatureModifier.NONE))
        effects {
            waterColor(OverworldBiomes.OVERWORLD_WATER)
            waterFogColor(OverworldBiomes.OVERWORLD_WATER_FOG)
            fogColor(FOG)
            skyColor(Color.BLACK)
            mood(AquaAmbientMoodSettings.CAVE)
        }
    }.build()
}
