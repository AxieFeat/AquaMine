package net.aquamine.server.potion

import net.aquamine.api.potion.PotionEffect
import net.aquamine.api.potion.PotionType
import net.aquamine.api.potion.PotionTypes

@JvmRecord
data class AquaPotionEffect(
    override val type: PotionType,
    override val amplifier: Int,
    override val duration: Int,
    override val ambient: Boolean,
    override val particles: Boolean,
    override val icon: Boolean
) : PotionEffect {

    override fun withType(type: PotionType): PotionEffect {
        return AquaPotionEffect(type, amplifier, duration, ambient, particles, icon)
    }

    override fun withAmplifier(amplifier: Int): PotionEffect {
        return AquaPotionEffect(type, amplifier, duration, ambient, particles, icon)
    }

    override fun withDuration(duration: Int): PotionEffect {
        return AquaPotionEffect(type, amplifier, duration, ambient, particles, icon)
    }

    override fun withAmbient(ambient: Boolean): PotionEffect {
        return AquaPotionEffect(type, amplifier, duration, ambient, particles, icon)
    }

    override fun withParticles(particles: Boolean): PotionEffect {
        return AquaPotionEffect(type, amplifier, duration, ambient, particles, icon)
    }

    override fun withIcon(icon: Boolean): PotionEffect {
        return AquaPotionEffect(type, amplifier, duration, ambient, particles, icon)
    }

    class Builder : PotionEffect.Builder {

        private var type: PotionType = PotionTypes.MOVEMENT_SPEED.get()
        private var amplifier: Int = 1
        private var duration: Int = 1
        private var ambient: Boolean = false
        private var particles: Boolean = true
        private var icon: Boolean = true

        override fun type(type: PotionType): PotionEffect.Builder {
            this.type = type
            return this
        }

        override fun amplifier(amplifier: Int): PotionEffect.Builder {
            this.amplifier = amplifier
            return this
        }

        override fun duration(duration: Int): PotionEffect.Builder {
            this.duration = duration
            return this
        }

        override fun ambient(ambient: Boolean): PotionEffect.Builder {
            this.ambient = ambient
            return this
        }

        override fun particles(particles: Boolean): PotionEffect.Builder {
            this.particles = particles
            return this
        }

        override fun icon(icon: Boolean): PotionEffect.Builder {
            this.icon = icon
            return this
        }

        override fun build(): PotionEffect = AquaPotionEffect(type, amplifier, duration, ambient, particles, icon)
    }

    object Factory : PotionEffect.Factory {

        override fun builder(): PotionEffect.Builder = Builder()
    }

}