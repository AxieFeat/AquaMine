package net.aquamine.server.potion

import net.aquamine.api.potion.PotionEffect
import net.aquamine.api.potion.PotionType
import net.aquamine.api.potion.PotionTypes

@JvmRecord
data class AquaPotionEffect(
    override val type: AquaPotionType,
    override val amplifier: Int,
    override val duration: Int,
    val flags: Byte
) : PotionEffect {

    override val ambient: Boolean
        get() = (flags.toInt() and AMBIENT_FLAG.toInt()) == AMBIENT_FLAG.toInt()
    override val particles: Boolean
        get() = (flags.toInt() and PARTICLES_FLAG.toInt()) == PARTICLES_FLAG.toInt()
    override val icon: Boolean
        get() = (flags.toInt() and ICON_FLAG.toInt()) == ICON_FLAG.toInt()

    override fun withType(type: PotionType): AquaPotionEffect {
        if(this.type == type) return this
        return AquaPotionEffect(type.downcast(), amplifier, duration, flags)
    }

    override fun withAmplifier(amplifier: Int): AquaPotionEffect {
        if(this.amplifier == amplifier) return this
        return AquaPotionEffect(type, amplifier, duration, flags)
    }

    override fun withDuration(duration: Int): AquaPotionEffect {
        if(this.duration == duration) return this
        return AquaPotionEffect(type, amplifier, duration, flags)
    }

    override fun withAmbient(ambient: Boolean): AquaPotionEffect {
        if(this.ambient == ambient) return this
        return AquaPotionEffect(type, amplifier, duration, flags)
    }

    override fun withParticles(particles: Boolean): AquaPotionEffect {
        if(this.particles == particles) return this
        return AquaPotionEffect(type, amplifier, duration, flags)
    }

    override fun withIcon(icon: Boolean): AquaPotionEffect {
        if(this.icon == icon) return this

        return AquaPotionEffect(type, amplifier, duration, flags)
    }

    // TODO: ambient, particles and icon functions
    class Builder : PotionEffect.Builder {

        private var type: AquaPotionType = PotionTypes.MOVEMENT_SPEED.get().downcast()
        private var amplifier: Int = 1
        private var duration: Int = 1
        private var flags: Byte = 0

        override fun type(type: PotionType): Builder = apply { this.type = type.downcast() }

        override fun amplifier(amplifier: Int): Builder = apply { this.amplifier = amplifier }

        override fun duration(duration: Int): Builder = apply { this.duration = duration }

        override fun ambient(ambient: Boolean): Builder = apply {}

        override fun particles(particles: Boolean): Builder = apply {}

        override fun icon(icon: Boolean): Builder = apply {}

        override fun build(): AquaPotionEffect = AquaPotionEffect(type, amplifier, duration, flags)
    }

    object Factory : PotionEffect.Factory {

        override fun builder(): PotionEffect.Builder = Builder()
    }

    companion object {

        /**
         * A flag indicating that this Potion is ambient (it came from a beacon).
         */
        const val AMBIENT_FLAG: Byte = 0x01

        /**
         * A flag indicating that this Potion has particles.
         */
        const val PARTICLES_FLAG: Byte = 0x02

        /**
         * A flag indicating that this Potion has an icon.
         */
        const val ICON_FLAG: Byte = 0x04

    }

}