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
 * Settings for ambient mood sounds that will play whilst in certain biomes.
 */
@ImmutableType
interface AmbientMoodSettings {

    /**
     * The sound that will be played.
     */
    val sound: SoundEvent

    /**
     * The delay, in ticks, before the sound starts playing.
     */
    val tickDelay: Int

    /**
     * The cubic range of possible positions to play the sound.
     *
     * The player is at the center of the cubic range, and the edge length is
     * `2 * blockSearchExtent + 1` (source: [The Official Minecraft Wiki](https://minecraft.fandom.com/wiki/Biome/JSON_format)).
     */
    val blockSearchExtent: Int

    /**
     * The offset that the client uses for the position to play the sound from.
     * This will be offset from the client's current position.
     */
    val offset: Double

    /**
     * A builder for ambient mood settings.
     */
    @BiomeDsl
    interface Builder : AbstractBuilder<AmbientMoodSettings> {

        /**
         * Sets the sound for the ambient mood settings.
         *
         * @param sound The sound.
         *
         * @return This builder.
         *
         * @see AmbientMoodSettings.sound
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun sound(sound: SoundEvent): Builder

        /**
         * Sets the delay, in ticks, for the ambient mood settings.
         *
         * @param delay The delay.
         *
         * @return This builder.
         *
         * @see AmbientMoodSettings.tickDelay
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun delay(delay: Int): Builder

        /**
         * Sets the block search extent for the ambient mood settings.
         *
         * @param extent The extent.
         *
         * @return This builder.
         *
         * @see AmbientMoodSettings.blockSearchExtent
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun searchExtent(extent: Int): Builder

        /**
         * Sets the offset for the ambient mood settings.
         *
         * @param offset The offset.
         *
         * @return This builder.
         *
         * @see AmbientMoodSettings.offset
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun offset(offset: Double): Builder
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(sound: SoundEvent, tickDelay: Int, blockSearchExtent: Int, offset: Double): AmbientMoodSettings

        fun builder(sound: SoundEvent): Builder
    }

    companion object {

        /**
         * Creates new ambient mood sound settings with the given values.
         *
         * @param sound The sound.
         * @param tickDelay The delay, in ticks, before playing the sound.
         * @param blockSearchExtent The cubic range of possible positions to
         * play the sound.
         * @param offset The offset from the client's position.
         *
         * @return New ambient mood sound settings.
         */
        @JvmStatic
        @Contract("_, _, _, _ -> new", pure = true)
        fun of(sound: SoundEvent, tickDelay: Int, blockSearchExtent: Int, offset: Double): AmbientMoodSettings =
            AquaMine.factory<Factory>().of(sound, tickDelay, blockSearchExtent, offset)

        /**
         * Creates a new builder for ambient mood settings.
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