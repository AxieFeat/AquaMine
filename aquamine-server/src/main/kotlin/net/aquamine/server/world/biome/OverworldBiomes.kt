package net.aquamine.server.world.biome

import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.util.Color
import net.aquamine.api.world.biome.Climate
import net.aquamine.api.world.biome.Precipitation
import net.aquamine.api.world.biome.GrassColorModifier
import net.aquamine.api.world.biome.TemperatureModifier
import net.aquamine.server.effect.AquaMusic
import net.aquamine.server.util.math.Maths
import net.aquamine.server.world.biome.data.AquaAmbientMoodSettings

@Suppress("UnusedPrivateMember") // Many parameters are there for when more biome data, such as generation and mob spawning, is added
object OverworldBiomes {

    // Standard colours
    @JvmField
    val OVERWORLD_WATER: Color = Color(4159204)
    @JvmField
    val OVERWORLD_WATER_FOG: Color = Color(329011)

    private val OVERWORLD_FOG = Color(12638463)
    private val COLD_WATER = Color(3750089)
    private val LUKEWARM_WATER = Color(4566514)
    private val LUKEWARM_WATER_FOG = Color(267827)
    private val WARM_WATER = Color(4445678)
    private val WARM_WATER_FOG = Color(270131)
    private val BADLANDS_FOLIAGE = Color(10387789)
    private val BADLANDS_GRASS = Color(9470285)
    private val SNOWY_WATER = Color(4020182)
    private val SWAMP_WATER = Color(6388580)
    private val SWAMP_WATER_FOG = Color(2302743)
    private val SWAMP_FOLIAGE = Color(6975545)
    private val MEADOW_WATER = Color(937679)
    private val MANGROVE_SWAMP_WATER = Color(3832426)
    private val MANGROVE_SWAMP_WATER_FOG = Color(5077600)
    private val MANGROVE_SWAMP_FOLIAGE = Color(9285927)

    // ==============================
    // None
    // ==============================

    @JvmStatic
    fun theVoid(): AquaBiome = createBiome(Precipitation.NONE, 0.5F, 0.5F)

    // ==============================
    // Taiga
    // ==============================

    @JvmStatic
    fun oldGrowthPineTaiga(): AquaBiome = oldGrowthTaiga(false)

    @JvmStatic
    fun oldGrowthSpruceTaiga(): AquaBiome = oldGrowthTaiga(true)

    @JvmStatic
    private fun oldGrowthTaiga(spruce: Boolean): AquaBiome =
        createBiome(Precipitation.RAIN, if (spruce) 0.25F else 0.3F, 0.8F, music = SoundEvents.MUSIC_BIOME_OLD_GROWTH_TAIGA.get())

    @JvmStatic
    fun taiga(): AquaBiome = taiga(false)

    @JvmStatic
    fun snowyTaiga(): AquaBiome = taiga(true)

    @JvmStatic
    private fun taiga(snowy: Boolean): AquaBiome {
        val precipitation = if (snowy) Precipitation.SNOW else Precipitation.RAIN
        val water = if (snowy) SNOWY_WATER else OVERWORLD_WATER
        return createBiome(precipitation, if (snowy) -0.5F else 0.25F, if (snowy) 0.4F else 0.8F, water)
    }

    // ==============================
    // Extreme Hills
    // ==============================

    @JvmStatic
    fun windsweptHills(): AquaBiome = windsweptHills(false)

    @JvmStatic
    fun windsweptForest(): AquaBiome = windsweptHills(true)

    @JvmStatic
    private fun windsweptHills(isEdge: Boolean): AquaBiome = createBiome(Precipitation.RAIN, 0.2F, 0.3F)

    // ==============================
    // Jungle
    // ==============================

    @JvmStatic
    fun sparseJungle(): AquaBiome = baseJungle(0.8F, isBamboo = false, isEdge = true, isLight = false)

    @JvmStatic
    fun jungle(): AquaBiome = baseJungle(0.9F, isBamboo = false, isEdge = false, isLight = true)

    @JvmStatic
    fun bambooJungle(): AquaBiome = baseJungle(0.9F, isBamboo = true, isEdge = false, isLight = true)

    @JvmStatic
    private fun baseJungle(downfall: Float, isBamboo: Boolean, isEdge: Boolean, isLight: Boolean): AquaBiome =
        createBiome(Precipitation.RAIN, 0.95F, downfall, music = SoundEvents.MUSIC_BIOME_JUNGLE.get())

    // ==============================
    // Mesa
    // ==============================

    @JvmStatic
    fun badlands(): AquaBiome = badlands(false)

    @JvmStatic
    fun woodedBadlands(): AquaBiome = badlands(true)

    @JvmStatic
    private fun badlands(wooded: Boolean): AquaBiome = biome {
        climate(Climate.of(Precipitation.NONE, 2F, 0F, TemperatureModifier.NONE))
        effects {
            waterColor(OVERWORLD_WATER)
            waterFogColor(OVERWORLD_WATER_FOG)
            fogColor(OVERWORLD_FOG)
            skyColor(calculateSkyColor(2F))
            foliageColor(BADLANDS_FOLIAGE)
            grassColor(BADLANDS_GRASS)
            mood(AquaAmbientMoodSettings.CAVE)
        }
    }

    // ==============================
    // Plains/Icy
    // ==============================

    @JvmStatic
    fun plains(): AquaBiome = plains(sunflowers = false, icy = false, spikes = false)

    @JvmStatic
    fun sunflowerPlains(): AquaBiome = plains(sunflowers = true, icy = false, spikes = false)

    @JvmStatic
    fun snowyPlains(): AquaBiome = plains(sunflowers = false, icy = true, spikes = false)

    @JvmStatic
    fun iceSpikes(): AquaBiome = plains(sunflowers = false, icy = true, spikes = true)

    @JvmStatic
    private fun plains(sunflowers: Boolean, icy: Boolean, spikes: Boolean): AquaBiome =
        createBiome(if (icy) Precipitation.SNOW else Precipitation.RAIN, if (icy) 0F else 0.8F, if (icy) 0F else 0.4F)

    // ==============================
    // Savanna
    // ==============================

    @JvmStatic
    fun savanna(): AquaBiome = savanna(shattered = false, plateau = false)

    @JvmStatic
    fun savannaPlateau(): AquaBiome = savanna(shattered = false, plateau = true)

    @JvmStatic
    fun windsweptSavanna(): AquaBiome = savanna(shattered = true, plateau = false)

    @JvmStatic
    private fun savanna(shattered: Boolean, plateau: Boolean): AquaBiome {
        val temperature = when {
            shattered -> 1.1F
            plateau -> 1F
            else -> 1.2F
        }
        return createBiome(Precipitation.NONE, temperature, 0F)
    }

    // ==============================
    // Beach
    // ==============================

    @JvmStatic
    fun beach(): AquaBiome = beach(snowy = false, stony = false)

    @JvmStatic
    fun snowyBeach(): AquaBiome = beach(snowy = true, stony = false)

    @JvmStatic
    fun stonyShore(): AquaBiome = beach(snowy = false, stony = true)

    @JvmStatic
    private fun beach(snowy: Boolean, stony: Boolean): AquaBiome {
        val temperature = when {
            snowy -> 0.05F
            stony -> 0.2F
            else -> 0.8F
        }
        val precipitation = if (snowy) Precipitation.SNOW else Precipitation.RAIN
        val water = if (snowy) SNOWY_WATER else OVERWORLD_WATER
        return createBiome(precipitation, temperature, if (!snowy && !stony) 0.4F else 0.3F, water)
    }

    // ==============================
    // Forest
    // ==============================

    @JvmStatic
    fun forest(): AquaBiome = forest(birch = false, tall = false, flower = false)

    @JvmStatic
    fun flowerForest(): AquaBiome = forest(birch = false, tall = false, flower = true)

    @JvmStatic
    fun birchForest(): AquaBiome = forest(birch = true, tall = false, flower = false)

    @JvmStatic
    fun oldGrowthBirchForest(): AquaBiome = forest(birch = true, tall = true, flower = false)

    @JvmStatic
    private fun forest(birch: Boolean, tall: Boolean, flower: Boolean): AquaBiome {
        val music = SoundEvents.MUSIC_BIOME_JUNGLE.get()
        return createBiome(Precipitation.RAIN, if (birch) 0.6F else 0.7F, if (birch) 0.6F else 0.8F, music = music)
    }

    @JvmStatic
    fun darkForest(): AquaBiome = biome {
        climate(Climate.of(Precipitation.RAIN, 0.7F, 0.8F, TemperatureModifier.NONE))
        effects {
            waterColor(OVERWORLD_WATER)
            waterFogColor(OVERWORLD_WATER_FOG)
            fogColor(OVERWORLD_FOG)
            skyColor(calculateSkyColor(0.7F))
            grassColorModifier(GrassColorModifier.DARK_FOREST)
            mood(AquaAmbientMoodSettings.CAVE)
            backgroundMusic(AquaMusic.game(SoundEvents.MUSIC_BIOME_JUNGLE.get()))
        }
    }

    // ==============================
    // Ocean
    // ==============================

    @JvmStatic
    fun coldOcean(): AquaBiome = coldOcean(false)

    @JvmStatic
    fun deepColdOcean(): AquaBiome = coldOcean(true)

    @JvmStatic
    fun ocean(): AquaBiome = ocean(false)

    @JvmStatic
    fun deepOcean(): AquaBiome = ocean(true)

    @JvmStatic
    fun lukewarmOcean(): AquaBiome = lukewarmOcean(false)

    @JvmStatic
    fun deepLukewarmOcean(): AquaBiome = lukewarmOcean(true)

    @JvmStatic
    fun warmOcean(): AquaBiome = baseOcean(WARM_WATER, WARM_WATER_FOG)

    @JvmStatic
    fun frozenOcean(): AquaBiome = frozenOcean(false)

    @JvmStatic
    fun deepFrozenOcean(): AquaBiome = frozenOcean(true)

    @JvmStatic
    private fun coldOcean(deep: Boolean): AquaBiome = baseOcean(COLD_WATER, OVERWORLD_WATER_FOG)

    @JvmStatic
    private fun ocean(deep: Boolean): AquaBiome = baseOcean(OVERWORLD_WATER, OVERWORLD_WATER_FOG)

    @JvmStatic
    private fun lukewarmOcean(deep: Boolean): AquaBiome = baseOcean(LUKEWARM_WATER, LUKEWARM_WATER_FOG)

    @JvmStatic
    private fun frozenOcean(deep: Boolean): AquaBiome {
        val temperature = if (deep) 0.5F else 0F
        return biome {
            climate(Climate.of(if (deep) Precipitation.RAIN else Precipitation.SNOW, temperature, 0.5F, TemperatureModifier.FROZEN))
            effects {
                waterColor(COLD_WATER)
                waterFogColor(OVERWORLD_WATER_FOG)
                fogColor(OVERWORLD_FOG)
                skyColor(calculateSkyColor(temperature))
                mood(AquaAmbientMoodSettings.CAVE)
            }
        }
    }

    @JvmStatic
    private fun baseOcean(waterColor: Color, waterFogColor: Color): AquaBiome =
        createBiome(Precipitation.RAIN, 0.5F, 0.5F, waterColor, waterFogColor)

    // ==============================
    // Desert
    // ==============================

    @JvmStatic
    fun desert(): AquaBiome = createBiome(Precipitation.NONE, 2F, 0F)

    // ==============================
    // River
    // ==============================

    @JvmStatic
    fun river(): AquaBiome = river(false)

    @JvmStatic
    fun frozenRiver(): AquaBiome = river(true)

    @JvmStatic
    private fun river(frozen: Boolean): AquaBiome {
        val precipitation = if (frozen) Precipitation.SNOW else Precipitation.RAIN
        return createBiome(precipitation, if (frozen) 0F else 0.5F, 0.5F, if (frozen) COLD_WATER else OVERWORLD_WATER)
    }

    // ==============================
    // Swamp
    // ==============================

    @JvmStatic
    fun swamp(): AquaBiome = baseSwamp(SWAMP_WATER, SWAMP_WATER_FOG, SWAMP_FOLIAGE)

    @JvmStatic
    fun mangroveSwamp(): AquaBiome = baseSwamp(MANGROVE_SWAMP_WATER, MANGROVE_SWAMP_WATER_FOG, MANGROVE_SWAMP_FOLIAGE)

    @JvmStatic
    private fun baseSwamp(waterColor: Color, waterFogColor: Color, foliageColor: Color): AquaBiome = biome {
        climate(Climate.of(Precipitation.RAIN, 0.8F, 0.9F, TemperatureModifier.NONE))
        effects {
            waterColor(waterColor)
            waterFogColor(waterFogColor)
            fogColor(OVERWORLD_FOG)
            skyColor(calculateSkyColor(0.8F))
            foliageColor(foliageColor)
            grassColorModifier(GrassColorModifier.SWAMP)
            mood(AquaAmbientMoodSettings.CAVE)
            backgroundMusic(AquaMusic.game(SoundEvents.MUSIC_BIOME_SWAMP.get()))
        }
    }

    // ==============================
    // Mushroom
    // ==============================

    @JvmStatic
    fun mushroomFields(): AquaBiome = createBiome(Precipitation.RAIN, 0.9F, 1F)

    // ==============================
    // Underground
    // ==============================

    @JvmStatic
    fun lushCaves(): AquaBiome = createBiome(Precipitation.RAIN, 0.5F, 0.5F, music = SoundEvents.MUSIC_BIOME_LUSH_CAVES.get())

    @JvmStatic
    fun dripstoneCaves(): AquaBiome = createBiome(Precipitation.RAIN, 0.8F, 0.4F, music = SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES.get())

    // ==============================
    // Mountain
    // ==============================

    @JvmStatic
    fun meadow(): AquaBiome = createBiome(Precipitation.RAIN, 0.5F, 0.8F, MEADOW_WATER, OVERWORLD_WATER_FOG, SoundEvents.MUSIC_BIOME_MEADOW.get())

    @JvmStatic
    fun frozenPeaks(): AquaBiome = createBiome(Precipitation.RAIN, -0.7F, 0.9F, music = SoundEvents.MUSIC_BIOME_FROZEN_PEAKS.get())

    @JvmStatic
    fun jaggedPeaks(): AquaBiome = createBiome(Precipitation.SNOW, -0.7F, 0.9F, music = SoundEvents.MUSIC_BIOME_JAGGED_PEAKS.get())

    @JvmStatic
    fun stonyPeaks(): AquaBiome = createBiome(Precipitation.RAIN, 1F, 0.3F, music = SoundEvents.MUSIC_BIOME_STONY_PEAKS.get())

    @JvmStatic
    fun snowySlopes(): AquaBiome = createBiome(Precipitation.SNOW, -0.3F, 0.9F, music = SoundEvents.MUSIC_BIOME_SNOWY_SLOPES.get())

    @JvmStatic
    fun grove(): AquaBiome = createBiome(Precipitation.SNOW, -0.2F, 0.8F, music = SoundEvents.MUSIC_BIOME_GROVE.get())

    @JvmStatic
    fun deepDark(): AquaBiome = createBiome(Precipitation.RAIN, 0.8F, 0.4F, music = SoundEvents.MUSIC_BIOME_DEEP_DARK.get())

    // ******************************
    // Helpers
    // ******************************

    @JvmStatic
    private fun createBiome(
        precipitation: Precipitation,
        temperature: Float,
        downfall: Float,
        waterColor: Color = OVERWORLD_WATER,
        waterFogColor: Color = OVERWORLD_WATER_FOG,
        music: SoundEvent? = null
    ): AquaBiome = biome {
        climate {
            precipitation(precipitation)
            temperature(temperature)
            downfall(downfall)
        }
        effects {
            waterColor(waterColor)
            waterFogColor(waterFogColor)
            fogColor(OVERWORLD_FOG)
            skyColor(calculateSkyColor(temperature))
            mood(AquaAmbientMoodSettings.CAVE)
            if (music != null) backgroundMusic(AquaMusic.game(music))
        }
    }

    @JvmStatic
    fun calculateSkyColor(temperature: Float): Color {
        val value = Maths.clamp(temperature / 3F, -1F, 1F)
        return Color.fromHsv(0.62222224F - value * 0.05F, 0.5F + value * 0.1F, 1F)
    }

    @JvmStatic
    private inline fun biome(builder: AquaBiome.Builder.() -> Unit): AquaBiome = AquaBiome.Builder().apply(builder).build()
}
