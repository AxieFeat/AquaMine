package net.aquamine.server.effect

import net.aquamine.api.effect.Music
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.server.effect.sound.AquaSoundEvent
import net.aquamine.serialization.Codec
import net.aquamine.serialization.codecs.RecordCodecBuilder

@JvmRecord
data class AquaMusic(
    override val sound: SoundEvent,
    override val minimumDelay: Int,
    override val maximumDelay: Int,
    override val replaceCurrentMusic: Boolean
) : Music {

    object Factory : Music.Factory {

        override fun of(sound: SoundEvent, minimumDelay: Int, maximumDelay: Int, replaceCurrentMusic: Boolean): Music =
            AquaMusic(sound, minimumDelay, maximumDelay, replaceCurrentMusic)
    }

    companion object {

        // Time in ticks constants
        private const val TEN_MINUTES = 12000
        private const val TWENTY_MINUTES = 24000

        @JvmField
        val CODEC: Codec<Music> = RecordCodecBuilder.create { instance ->
            instance.group(
                AquaSoundEvent.DIRECT_CODEC.fieldOf("sound").getting { it.sound },
                Codec.INT.fieldOf("min_delay").getting { it.minimumDelay },
                Codec.INT.fieldOf("max_delay").getting { it.maximumDelay },
                Codec.BOOLEAN.fieldOf("replace_current_music").getting { it.replaceCurrentMusic }
            ).apply(instance, ::AquaMusic)
        }

        @JvmStatic
        fun game(sound: SoundEvent): AquaMusic = AquaMusic(sound, TEN_MINUTES, TWENTY_MINUTES, false)
    }
}
