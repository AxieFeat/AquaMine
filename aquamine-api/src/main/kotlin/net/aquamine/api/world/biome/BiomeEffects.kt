package net.aquamine.api.world.biome

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.aquamine.annotations.dsl.BiomeDsl
import net.kyori.adventure.builder.AbstractBuilder
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.effect.Music
import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.util.Color
import java.util.function.Consumer

/**
 * The effects of a biome. These control various things, including coloring,
 * ambient particles, sounds, and music.
 */
@ImmutableType
interface BiomeEffects {

    /**
     * The color that fog will appear when in the biome.
     */
    val fogColor: Color

    /**
     * The color that water will appear when in the biome.
     */
    val waterColor: Color

    /**
     * The color that water will appear when viewed through fog in the biome.
     */
    val waterFogColor: Color

    /**
     * The color that the sky will appear when in the biome.
     */
    val skyColor: Color

    /**
     * The modifier for the color of grass in the biome.
     */
    val grassColorModifier: GrassColorModifier

    /**
     * The color that foliage, such as tree leaves and vines, will appear when
     * in the biome.
     */
    val foliageColor: Color?

    /**
     * The color that grass, such as grass blocks and tall grass, will appear
     * when in the biome.
     */
    val grassColor: Color?

    /**
     * Settings for ambient particle effects that may appear randomly when in
     * the biome.
     */
    val ambientParticleSettings: AmbientParticleSettings?

    /**
     * The ambient sound that should be played and put on loop until the biome
     * is left.
     */
    val ambientLoopSound: SoundEvent?

    /**
     * Settings for the ambient mood sound that will start playing after a
     * fixed delay and in a specific range.
     */
    val ambientMoodSettings: AmbientMoodSettings?

    /**
     * Settings for the ambient additions sound that may play randomly when in
     * the biome.
     */
    val ambientAdditionsSettings: AmbientAdditionsSettings?

    /**
     * The music that will be played in the background when in the biome.
     */
    val backgroundMusic: Music?

    /**
     * A builder for biome effects.
     */
    @BiomeDsl
    interface Builder : AbstractBuilder<BiomeEffects> {

        /**
         * Sets the fog color of the biome effects to the given [color].
         *
         * @param color The color.
         *
         * @return This builder.
         *
         * @see BiomeEffects.fogColor
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun fogColor(color: Color): Builder

        /**
         * Sets the water color of the biome effects to the given [color].
         *
         * @param color The color.
         *
         * @return This builder.
         *
         * @see BiomeEffects.waterColor
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun waterColor(color: Color): Builder

        /**
         * Sets the water fog color of the biome effects to the given [color].
         *
         * @param color The color.
         *
         * @return This builder.
         *
         * @see BiomeEffects.waterFogColor
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun waterFogColor(color: Color): Builder

        /**
         * Sets the sky color of the biome effects to the given [color].
         *
         * @param color The color.
         *
         * @return This builder.
         *
         * @see BiomeEffects.skyColor
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun skyColor(color: Color): Builder

        /**
         * Sets the grass color modifier of the biome effects to the given
         * [modifier].
         *
         * @param modifier The grass color modifier.
         *
         * @return This builder.
         *
         * @see BiomeEffects.grassColorModifier
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun grassColorModifier(modifier: GrassColorModifier): Builder

        /**
         * Sets the foliage color of the biome effects to the given [color].
         *
         * @param color The color.
         *
         * @return This builder.
         *
         * @see BiomeEffects.foliageColor
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun foliageColor(color: Color?): Builder

        /**
         * Sets the grass color of the biome effects to the given [color].
         *
         * @param color The color.
         *
         * @return This builder.
         *
         * @see BiomeEffects.grassColor
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun grassColor(color: Color?): Builder

        /**
         * Sets the ambient particle settings of the biome effects to the
         * given [settings].
         *
         * @param settings The settings.
         *
         * @return This builder.
         *
         * @see BiomeEffects.ambientParticleSettings
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun particles(settings: AmbientParticleSettings?): Builder

        /**
         * Applies the given [builder] to an ambient particle settings builder,
         * builds the result, and sets the ambient particle settings to the
         * built result.
         *
         * @param type The type.
         * @param builder The builder.
         *
         * @return This builder.
         *
         * @see BiomeEffects.ambientParticleSettings
         */
        @BiomeDsl
        @Contract("_, _ -> this", mutates = "this")
        fun particles(type: ParticleType, builder: Consumer<AmbientParticleSettings.Builder>): Builder =
            particles(AmbientParticleSettings.builder(type).apply { builder.accept(this) }.build())

        /**
         * Sets the ambient loop sound of the biome effects to the given
         * [sound].
         *
         * @param sound The sound.
         *
         * @return This builder.
         *
         * @see BiomeEffects.ambientLoopSound
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun loopSound(sound: SoundEvent?): Builder

        /**
         * Sets the ambient mood settings of the biome effects to the given
         * [settings].
         *
         * @param settings The settings.
         *
         * @return This builder.
         *
         * @see BiomeEffects.ambientMoodSettings
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun mood(settings: AmbientMoodSettings?): Builder

        /**
         * Applies the given [builder] to an ambient mood settings builder,
         * builds the result, and sets the ambient mood settings to the
         * built result.
         *
         * @param sound The sound.
         * @param builder The builder.
         *
         * @return This builder.
         *
         * @see BiomeEffects.ambientMoodSettings
         */
        @BiomeDsl
        @Contract("_, _ -> this", mutates = "this")
        fun mood(sound: SoundEvent, builder: Consumer<AmbientMoodSettings.Builder>): Builder =
            mood(AmbientMoodSettings.builder(sound).apply { builder.accept(this) }.build())

        /**
         * Sets the ambient additions settings of the biome effects to the
         * given [settings].
         *
         * @param settings The settings.
         *
         * @return This builder.
         *
         * @see BiomeEffects.ambientAdditionsSettings
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun additions(settings: AmbientAdditionsSettings?): Builder

        /**
         * Applies the given [builder] to an ambient addition settings
         * builder, builds the result, and sets the ambient additions settings
         * to the built result.
         *
         * @param sound The sound.
         * @param builder The builder.
         *
         * @return This builder.
         *
         * @see BiomeEffects.ambientAdditionsSettings
         */
        @BiomeDsl
        @Contract("_, _ -> this", mutates = "this")
        fun additions(sound: SoundEvent, builder: Consumer<AmbientAdditionsSettings.Builder>): Builder =
            additions(AmbientAdditionsSettings.builder(sound).apply { builder.accept(this) }.build())

        /**
         * Sets the background music of the biome effects to the given [music].
         *
         * @param music The music.
         *
         * @return This builder.
         *
         * @see BiomeEffects.ambientParticleSettings
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun backgroundMusic(music: Music?): Builder
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun builder(): Builder
    }

    companion object {

        /**
         * Creates a new builder for biome effects.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = AquaMine.factory<Factory>().builder()
    }
}
