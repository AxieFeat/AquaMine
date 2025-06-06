package net.aquamine.api.world.biome

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.aquamine.annotations.dsl.BiomeDsl
import net.kyori.adventure.builder.AbstractBuilder
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.effect.sound.SoundEvent

/**
 * Settings for ambient sounds that may play randomly when in the biome, with
 * a fixed chance.
 */
@ImmutableType
interface AmbientAdditionsSettings {

    /**
     * The sound that might be played.
     */
    val sound: SoundEvent

    /**
     * The probability of the sound playing on any tick.
     */
    val probability: Double

    /**
     * A builder for ambient additions settings.
     */
    @BiomeDsl
    interface Builder : AbstractBuilder<AmbientAdditionsSettings> {

        /**
         * Sets the sound for the ambient additions settings to the given
         * [sound].
         *
         * @param sound The sound.
         *
         * @return This builder.
         *
         * @see AmbientAdditionsSettings.sound
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun sound(sound: SoundEvent): Builder

        /**
         * Sets the probability for the ambient additions settings to the
         * given [probability].
         *
         * @param probability The probability.
         *
         * @return This builder.
         *
         * @see AmbientAdditionsSettings.probability
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun probability(probability: Double): Builder
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(sound: SoundEvent, probability: Double): AmbientAdditionsSettings

        fun builder(sound: SoundEvent): Builder
    }

    companion object {

        /**
         * Creates new ambient addition sound settings with the given values.
         *
         * @param sound The sound.
         * @param probability The probability.
         *
         * @return New ambient addition sound settings.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        fun of(sound: SoundEvent, probability: Double): AmbientAdditionsSettings =
            AquaMine.factory<Factory>().of(sound, probability)

        /**
         * Creates a new builder for ambient additions settings.
         *
         * @param sound The sound.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun builder(sound: SoundEvent): Builder = AquaMine.factory<Factory>().builder(sound)
    }
}