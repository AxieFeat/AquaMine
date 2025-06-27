package net.aquamine.server.world.biome

import net.aquamine.api.effect.particle.ParticleTypes
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.util.Color
import net.aquamine.api.world.biome.AmbientAdditionsSettings
import net.aquamine.api.world.biome.AmbientMoodSettings
import net.aquamine.api.world.biome.AmbientParticleSettings
import net.aquamine.api.world.biome.Climate
import net.aquamine.api.world.biome.Precipitation
import net.aquamine.api.world.biome.TemperatureModifier
import net.aquamine.server.effect.AquaMusic

object NetherBiomes {

    private val SKY_COLOR = OverworldBiomes.calculateSkyColor(2F)

    private const val WASTES_FOG = 3344392 // Red: 51, Green: 8, Blue: 8
    private const val VALLEY_FOG = 1787717 // Red: 27, Green: 71, Blue: 69
    private const val DELTAS_FOG = 6840176 // Red: 104, Green: 95, Blue: 112
    private const val CRIMSON_FOG = 3343107 // Red: 51, Green: 3, Blue: 3
    private const val WARPED_FOG = 1705242 // Red: 26, Green: 5, Blue: 26

    private val WASTES_LOOP = SoundEvents.AMBIENT_NETHER_WASTES_LOOP.get()
    private val VALLEY_LOOP = SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP.get()
    private val DELTAS_LOOP = SoundEvents.AMBIENT_BASALT_DELTAS_LOOP.get()
    private val CRIMSON_LOOP = SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP.get()
    private val WARPED_LOOP = SoundEvents.AMBIENT_WARPED_FOREST_LOOP.get()

    private val WASTES_MOOD = SoundEvents.AMBIENT_NETHER_WASTES_MOOD.get()
    private val VALLEY_MOOD = SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD.get()
    private val DELTAS_MOOD = SoundEvents.AMBIENT_BASALT_DELTAS_MOOD.get()
    private val CRIMSON_MOOD = SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD.get()
    private val WARPED_MOOD = SoundEvents.AMBIENT_WARPED_FOREST_MOOD.get()

    private val WASTES_ADDITIONS = SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS.get()
    private val VALLEY_ADDITIONS = SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS.get()
    private val DELTAS_ADDITIONS = SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS.get()
    private val CRIMSON_ADDITIONS = SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS.get()
    private val WARPED_ADDITIONS = SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS.get()

    private val WASTES_MUSIC = SoundEvents.MUSIC_BIOME_NETHER_WASTES.get()
    private val VALLEY_MUSIC = SoundEvents.MUSIC_BIOME_SOUL_SAND_VALLEY.get()
    private val DELTAS_MUSIC = SoundEvents.MUSIC_BIOME_BASALT_DELTAS.get()
    private val CRIMSON_MUSIC = SoundEvents.MUSIC_BIOME_CRIMSON_FOREST.get()
    private val WARPED_MUSIC = SoundEvents.MUSIC_BIOME_WARPED_FOREST.get()

    private val VALLEY_PARTICLES = AmbientParticleSettings.of(ParticleTypes.ASH.get(), null, 0.00625F)
    private val DELTAS_PARTICLES = AmbientParticleSettings.of(ParticleTypes.WHITE_ASH.get(), null, 0.118093334F)
    private val CRIMSON_PARTICLES = AmbientParticleSettings.of(ParticleTypes.CRIMSON_SPORE.get(), null, 0.025F)
    private val WARPED_PARTICLES = AmbientParticleSettings.of(ParticleTypes.WARPED_SPORE.get(), null, 0.01428F)

    @JvmStatic
    fun netherWastes(): AquaBiome = baseNether(WASTES_FOG, WASTES_LOOP, WASTES_MOOD, WASTES_ADDITIONS, WASTES_MUSIC)

    @JvmStatic
    fun soulSandValley(): AquaBiome = baseNether(VALLEY_FOG, VALLEY_LOOP, VALLEY_MOOD, VALLEY_ADDITIONS, VALLEY_MUSIC, VALLEY_PARTICLES)

    @JvmStatic
    fun basaltDeltas(): AquaBiome = baseNether(DELTAS_FOG, DELTAS_LOOP, DELTAS_MOOD, DELTAS_ADDITIONS, DELTAS_MUSIC, DELTAS_PARTICLES)

    @JvmStatic
    fun crimsonForest(): AquaBiome = baseNether(CRIMSON_FOG, CRIMSON_LOOP, CRIMSON_MOOD, CRIMSON_ADDITIONS, CRIMSON_MUSIC, CRIMSON_PARTICLES)

    @JvmStatic
    fun warpedForest(): AquaBiome = baseNether(WARPED_FOG, WARPED_LOOP, WARPED_MOOD, WARPED_ADDITIONS, WARPED_MUSIC, WARPED_PARTICLES)

    @JvmStatic
    private fun baseNether(
        fog: Int,
        loop: SoundEvent,
        mood: SoundEvent,
        additions: SoundEvent,
        music: SoundEvent,
        particles: AmbientParticleSettings? = null
    ): AquaBiome = AquaBiome.Builder().apply {
        climate(Climate.of(Precipitation.NONE, 2F, 0F, TemperatureModifier.NONE))
        effects {
            waterColor(OverworldBiomes.OVERWORLD_WATER)
            waterFogColor(OverworldBiomes.OVERWORLD_WATER_FOG)
            fogColor(Color(fog))
            skyColor(SKY_COLOR)
            particles(particles)
            loopSound(loop)
            mood(AmbientMoodSettings.of(mood, 6000, 8, 2.0))
            additions(AmbientAdditionsSettings.of(additions, 0.0111))
            backgroundMusic(AquaMusic.game(music))
        }
    }.build()
}
