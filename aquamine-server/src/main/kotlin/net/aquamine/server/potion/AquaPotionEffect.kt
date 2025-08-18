package net.aquamine.server.potion

import net.aquamine.api.potion.PotionEffect
import net.aquamine.api.potion.PotionType
import net.aquamine.api.potion.PotionTypes
import net.aquamine.server.registry.AquaRegistries
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.compound

@JvmRecord
data class AquaPotionEffect(
    override val type: AquaPotionType,
    override val amplifier: Byte,
    override val duration: Int,
    val flags: Byte,
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

    override fun withAmplifier(amplifier: Byte): AquaPotionEffect {
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

    override fun toBuilder(): PotionEffect.Builder {
        return Builder(this)
    }

    // TODO: ambient, particles and icon functions
    class Builder() : PotionEffect.Builder {

        private var type: AquaPotionType = PotionTypes.MOVEMENT_SPEED.get().downcast()
        private var amplifier: Byte = 1
        private var duration: Int = 1
        private var flags: Byte = 0

        constructor(potionEffect: AquaPotionEffect) : this() {
            type = potionEffect.type
            amplifier = potionEffect.amplifier
            duration = potionEffect.duration
            flags = potionEffect.flags
        }

        override fun type(type: PotionType): Builder = apply { this.type = type.downcast() }

        override fun amplifier(amplifier: Byte): Builder = apply { this.amplifier = amplifier }

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

        private const val ID_TAG = "Id"
        private const val AMPLIFIER_TAG = "Amplifier"
        private const val DURATION_TAG = "Duration"
        private const val AMBIENT_TAG = "Ambient"
        private const val PARTICLES_TAG = "ShowParticles"
        private const val ICON_TAG = "ShowIcon"

        @JvmStatic
        fun load(data: CompoundTag): AquaPotionEffect {
            val type = AquaRegistries.POTION_TYPE.get(data.getByte(ID_TAG).toInt() - 1)!!
            val amplifier = (data.getByte(AMPLIFIER_TAG) + 1).toByte()
            val duration = data.getInt(DURATION_TAG)
            val ambient = data.getBoolean(AMBIENT_TAG)
            val particles = data.getBoolean(PARTICLES_TAG)
            val icon = data.getBoolean(ICON_TAG)

            return Builder()
                .type(type)
                .amplifier(amplifier)
                .duration(duration)
                .ambient(ambient)
                .particles(particles)
                .icon(icon)
                .build()
        }

        @JvmStatic
        fun save(effect: PotionEffect): CompoundTag = compound {
            putByte(ID_TAG, (AquaRegistries.POTION_TYPE.getId(effect.type.downcast()) + 1).toByte())
            putByte(AMPLIFIER_TAG, (effect.amplifier - 1).toByte())
            putInt(DURATION_TAG, effect.duration)
            putBoolean(AMBIENT_TAG, effect.ambient)
            putBoolean(PARTICLES_TAG, effect.particles)
            putBoolean(ICON_TAG, effect.icon)
        }
    }
}
