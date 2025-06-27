package net.aquamine.server.world.biome.data

import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.world.biome.AmbientMoodSettings
import net.aquamine.server.effect.sound.AquaSoundEvent
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.codecs.RecordCodecBuilder

@JvmRecord
data class AquaAmbientMoodSettings(
    override val sound: SoundEvent,
    override val tickDelay: Int,
    override val blockSearchExtent: Int,
    override val offset: Double
) : AmbientMoodSettings {

    class Builder(private var sound: SoundEvent) : AmbientMoodSettings.Builder {

        private var tickDelay = 0
        private var searchExtent = 0
        private var offset = 0.0

        override fun sound(sound: SoundEvent): AmbientMoodSettings.Builder = apply { this.sound = sound }

        override fun delay(delay: Int): AmbientMoodSettings.Builder = apply { tickDelay = delay }

        override fun searchExtent(extent: Int): AmbientMoodSettings.Builder = apply { searchExtent = extent }

        override fun offset(offset: Double): AmbientMoodSettings.Builder = apply { this.offset = offset }

        override fun build(): AmbientMoodSettings = AquaAmbientMoodSettings(sound, tickDelay, searchExtent, offset)
    }

    object Factory : AmbientMoodSettings.Factory {

        override fun of(sound: SoundEvent, tickDelay: Int, blockSearchExtent: Int, offset: Double): AmbientMoodSettings =
            AquaAmbientMoodSettings(sound, tickDelay, blockSearchExtent, offset)

        override fun builder(sound: SoundEvent): AmbientMoodSettings.Builder = Builder(sound)
    }

    companion object {

        @JvmField
        val CAVE: AquaAmbientMoodSettings = AquaAmbientMoodSettings(SoundEvents.AMBIENT_CAVE.get(), 6000, 8, 2.0)

        @JvmField
        val CODEC: Codec<AmbientMoodSettings> = RecordCodecBuilder.create { instance ->
            instance.group(
                AquaSoundEvent.DIRECT_CODEC.fieldOf("sound").getting { it.sound },
                Codec.INT.fieldOf("tick_delay").getting { it.tickDelay },
                Codec.INT.fieldOf("block_search_extent").getting { it.blockSearchExtent },
                Codec.DOUBLE.fieldOf("offset").getting { it.offset }
            ).apply(instance, ::AquaAmbientMoodSettings)
        }
    }
}
