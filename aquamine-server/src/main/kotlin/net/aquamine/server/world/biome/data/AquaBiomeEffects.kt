package net.aquamine.server.world.biome.data

import net.aquamine.api.effect.Music
import net.aquamine.server.effect.AquaMusic
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.util.Color
import net.aquamine.api.world.biome.AmbientAdditionsSettings
import net.aquamine.api.world.biome.AmbientMoodSettings
import net.aquamine.api.world.biome.AmbientParticleSettings
import net.aquamine.api.world.biome.BiomeEffects
import net.aquamine.api.world.biome.GrassColorModifier
import net.aquamine.server.effect.sound.AquaSoundEvent
import net.aquamine.server.util.ColorUtil
import net.aquamine.server.util.serialization.EnumCodecs
import net.aquamine.serialization.Codec
import net.aquamine.serialization.codecs.RecordCodecBuilder
import java.util.Optional

@JvmRecord
data class AquaBiomeEffects(
    override val fogColor: Color,
    override val waterColor: Color,
    override val waterFogColor: Color,
    override val skyColor: Color,
    override val grassColorModifier: GrassColorModifier = GrassColorModifier.NONE,
    override val foliageColor: Color? = null,
    override val grassColor: Color? = null,
    override val ambientParticleSettings: AmbientParticleSettings? = null,
    override val ambientLoopSound: SoundEvent? = null,
    override val ambientMoodSettings: AmbientMoodSettings? = null,
    override val ambientAdditionsSettings: AmbientAdditionsSettings? = null,
    override val backgroundMusic: Music? = null
) : BiomeEffects {

    class Builder : BiomeEffects.Builder {

        private var fog = Color.BLACK
        private var water = Color.BLACK
        private var waterFog = Color.BLACK
        private var sky = Color.BLACK
        private var grassModifier = GrassColorModifier.NONE
        private var foliage: Color? = null
        private var grass: Color? = null
        private var particles: AmbientParticleSettings? = null
        private var loopSound: SoundEvent? = null
        private var mood: AmbientMoodSettings? = null
        private var additions: AmbientAdditionsSettings? = null
        private var backgroundMusic: Music? = null

        override fun fogColor(color: Color): BiomeEffects.Builder = apply { fog = color }

        override fun waterColor(color: Color): BiomeEffects.Builder = apply { water = color }

        override fun waterFogColor(color: Color): BiomeEffects.Builder = apply { waterFog = color }

        override fun skyColor(color: Color): BiomeEffects.Builder = apply { sky = color }

        override fun grassColorModifier(modifier: GrassColorModifier): BiomeEffects.Builder = apply { grassModifier = modifier }

        override fun foliageColor(color: Color?): BiomeEffects.Builder = apply { foliage = color }

        override fun grassColor(color: Color?): BiomeEffects.Builder = apply { grass = color }

        override fun particles(settings: AmbientParticleSettings?): BiomeEffects.Builder = apply { particles = settings }

        override fun loopSound(sound: SoundEvent?): BiomeEffects.Builder = apply { loopSound = sound }

        override fun mood(settings: AmbientMoodSettings?): BiomeEffects.Builder = apply { mood = settings }

        override fun additions(settings: AmbientAdditionsSettings?): BiomeEffects.Builder = apply { additions = settings }

        override fun backgroundMusic(music: Music?): BiomeEffects.Builder = apply { backgroundMusic = music }

        override fun build(): BiomeEffects =
            AquaBiomeEffects(fog, water, waterFog, sky, grassModifier, foliage, grass, particles, loopSound, mood, additions, backgroundMusic)
    }

    object Factory : BiomeEffects.Factory {

        override fun builder(): BiomeEffects.Builder = Builder()
    }

    companion object {

        @JvmField
        val DEFAULT: BiomeEffects = Builder().build()
        @JvmField
        val CODEC: Codec<BiomeEffects> = RecordCodecBuilder.create { instance ->
            instance.group(
                ColorUtil.CODEC.fieldOf("fog_color").getting { it.fogColor },
                ColorUtil.CODEC.fieldOf("water_color").getting { it.waterColor },
                ColorUtil.CODEC.fieldOf("water_fog_color").getting { it.waterFogColor },
                ColorUtil.CODEC.fieldOf("sky_color").getting { it.skyColor },
                EnumCodecs.GRASS_COLOR_MODIFIER.fieldOf("grass_color_modifier").getting { it.grassColorModifier },
                ColorUtil.CODEC.optionalFieldOf("foliage_color").getting { Optional.ofNullable(it.foliageColor) },
                ColorUtil.CODEC.optionalFieldOf("grass_color").getting { Optional.ofNullable(it.grassColor) },

                // TODO Fix particle settings serialization. It not works with Via*
                AquaAmbientParticleSettings.CODEC.optionalFieldOf("particle").getting { Optional.ofNullable(null) },
                AquaSoundEvent.DIRECT_CODEC.optionalFieldOf("ambient_sound").getting { Optional.ofNullable(it.ambientLoopSound) },
                AquaAmbientMoodSettings.CODEC.optionalFieldOf("mood_sound").getting { Optional.ofNullable(it.ambientMoodSettings) },
                AquaAmbientAdditionsSettings.CODEC.optionalFieldOf("additions_sound").getting { Optional.ofNullable(it.ambientAdditionsSettings) },
                AquaMusic.CODEC.optionalFieldOf("music").getting { Optional.ofNullable(it.backgroundMusic) }
            ).apply(instance) { fog, water, waterFog, sky, modifier, foliage, grass, particles, loopSound, mood, additions, music ->
                AquaBiomeEffects(fog, water, waterFog, sky, modifier, foliage.orElse(null), grass.orElse(null), particles.orElse(null),
                    loopSound.orElse(null), mood.orElse(null), additions.orElse(null), music.orElse(null))
            }
        }
    }
}
