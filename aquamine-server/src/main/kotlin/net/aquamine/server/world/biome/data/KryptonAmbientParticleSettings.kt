package net.aquamine.server.world.biome.data

import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.api.world.biome.AmbientParticleSettings
import net.aquamine.server.util.serialization.Codecs
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.codecs.RecordCodecBuilder

@JvmRecord
data class KryptonAmbientParticleSettings(
    override val type: ParticleType,
    override val data: ParticleData?,
    override val probability: Float
) : AmbientParticleSettings {

    constructor(type: ParticleType, probability: Float) : this(type, null, probability)

    class Builder(private var type: ParticleType) : AmbientParticleSettings.Builder {

        private var data: ParticleData? = null
        private var probability = 0F

        override fun type(type: ParticleType): AmbientParticleSettings.Builder = apply { this.type = type }

        override fun data(data: ParticleData?): AmbientParticleSettings.Builder = apply { this.data = data }

        override fun probability(probability: Float): AmbientParticleSettings.Builder = apply { this.probability = probability }

        override fun build(): AmbientParticleSettings = KryptonAmbientParticleSettings(type, data, probability)
    }

    object Factory : AmbientParticleSettings.Factory {

        override fun of(type: ParticleType, data: ParticleData?, probability: Float): AmbientParticleSettings =
            KryptonAmbientParticleSettings(type, data, probability)

        override fun builder(type: ParticleType): AmbientParticleSettings.Builder = Builder(type)
    }

    companion object {

        @JvmField
        val CODEC: Codec<AmbientParticleSettings> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codecs.PARTICLE.fieldOf("particle").getting { it.type },
                Codec.FLOAT.fieldOf("probability").getting { it.probability }
            ).apply(instance, ::KryptonAmbientParticleSettings)
        }
    }
}
