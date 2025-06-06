package net.aquamine.api.effect

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.effect.sound.SoundEvent

/**
 * Music that may be played. This has a minimum and maximum delay before the
 * music will start playing, and the music can start playing anywhere in
 * between this time frame.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
interface Music {

    /**
     * The sound that will be played.
     */
    val sound: SoundEvent

    /**
     * The minimum delay before the music will start playing.
     */
    val minimumDelay: Int

    /**
     * The maximum delay before the music will start playing.
     */
    val maximumDelay: Int

    /**
     * If this music should replace any currently playing music.
     *
     * For example, the menu, credits, and ender dragon fight music will do
     * this.
     */
    @get:JvmName("replaceCurrentMusic")
    val replaceCurrentMusic: Boolean

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(sound: SoundEvent, minimumDelay: Int, maximumDelay: Int, replaceCurrentMusic: Boolean): Music
    }

    companion object {

        /**
         * Creates new music with the given values.
         *
         * @param sound The sound that will be played.
         * @param minimumDelay The minimum delay before starting.
         * @param maximumDelay The maximum delay before starting.
         *
         * @param replaceCurrentMusic If the current music should be replaced.
         */
        @JvmStatic
        @Contract("_, _, _, _ -> new", pure = true)
        fun of(sound: SoundEvent, minimumDelay: Int, maximumDelay: Int, replaceCurrentMusic: Boolean): Music =
            AquaMine.factory<Factory>().of(sound, minimumDelay, maximumDelay, replaceCurrentMusic)
    }
}
