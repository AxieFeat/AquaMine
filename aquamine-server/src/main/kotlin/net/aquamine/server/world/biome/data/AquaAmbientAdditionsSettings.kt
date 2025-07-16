package net.aquamine.server.world.biome.data

import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.world.biome.AmbientAdditionsSettings
import net.aquamine.server.effect.sound.AquaSoundEvent
import net.aquamine.serialization.Codec
import net.aquamine.serialization.codecs.RecordCodecBuilder

@JvmRecord
data class AquaAmbientAdditionsSettings(override val sound: SoundEvent, override val probability: Double) : AmbientAdditionsSettings {

    class Builder(private var sound: SoundEvent) : AmbientAdditionsSettings.Builder {

        private var probability = 0.0

        override fun sound(sound: SoundEvent): AmbientAdditionsSettings.Builder = apply { this.sound = sound }

        override fun probability(probability: Double): AmbientAdditionsSettings.Builder = apply { this.probability = probability }

        override fun build(): AmbientAdditionsSettings = AquaAmbientAdditionsSettings(sound, probability)
    }

    object Factory : AmbientAdditionsSettings.Factory {

        override fun of(sound: SoundEvent, probability: Double): AmbientAdditionsSettings = AquaAmbientAdditionsSettings(sound, probability)

        override fun builder(sound: SoundEvent): AmbientAdditionsSettings.Builder = Builder(sound)
    }

    companion object {

        @JvmField
        val CODEC: Codec<AmbientAdditionsSettings> = RecordCodecBuilder.create { instance ->
            instance.group(
                AquaSoundEvent.DIRECT_CODEC.fieldOf("sound").getting { it.sound },
                Codec.DOUBLE.fieldOf("probability").getting { it.probability }
            ).apply(instance, ::AquaAmbientAdditionsSettings)
        }
    }
}
