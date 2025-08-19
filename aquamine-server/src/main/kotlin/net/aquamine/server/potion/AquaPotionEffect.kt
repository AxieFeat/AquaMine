package net.aquamine.server.potion

import net.aquamine.api.potion.PotionEffect
import net.aquamine.api.potion.PotionType
import net.aquamine.api.potion.PotionTypes
import net.aquamine.server.registry.AquaRegistries
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.compound
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or

@JvmRecord
data class AquaPotionEffect(
    override val type: AquaPotionType,
    override val amplifier: Byte,
    override val duration: Int,
    val flags: Byte
) : PotionEffect {

    override val ambient: Boolean
        get() = flags and AMBIENT_FLAG == AMBIENT_FLAG
    override val particles: Boolean
        get() = flags and PARTICLES_FLAG == PARTICLES_FLAG
    override val icon: Boolean
        get() = flags and ICON_FLAG == ICON_FLAG

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
        return AquaPotionEffect(type, amplifier, duration, setFlag(flags, AMBIENT_FLAG, ambient))
    }

    override fun withParticles(particles: Boolean): AquaPotionEffect {
        if(this.particles == particles) return this
        return AquaPotionEffect(type, amplifier, duration, setFlag(flags, PARTICLES_FLAG, particles))
    }

    override fun withIcon(icon: Boolean): AquaPotionEffect {
        if(this.icon == icon) return this

        return AquaPotionEffect(type, amplifier, duration, setFlag(flags, ICON_FLAG, icon))
    }

    override fun toBuilder(): PotionEffect.Builder = Builder(this)

    class Builder() : PotionEffect.Builder {

        private var type: AquaPotionType = PotionTypes.MOVEMENT_SPEED.get().downcast()
        private var amplifier: Byte = 1
        private var duration: Int = 1
        private var flags: Byte = DEFAULT_FLAGS

        constructor(potionEffect: AquaPotionEffect) : this() {
            this.type = potionEffect.type
            this.amplifier = potionEffect.amplifier
            this.duration = potionEffect.duration
            this.flags = potionEffect.flags
        }

        override fun type(type: PotionType): Builder = apply { this.type = type.downcast() }

        override fun amplifier(amplifier: Byte): Builder = apply { this.amplifier = amplifier }

        override fun duration(duration: Int): Builder = apply { this.duration = duration }

        override fun ambient(ambient: Boolean): Builder = apply { this.flags = setFlag(flags, AMBIENT_FLAG, ambient) }

        override fun particles(particles: Boolean): Builder = apply { this.flags = setFlag(flags, PARTICLES_FLAG, particles) }

        override fun icon(icon: Boolean): Builder = apply { this.flags = setFlag(flags, ICON_FLAG,icon) }

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

        /**
         * This is default potion flags.
         * Icon: `true`, particles: `true`, ambient: `false`
         */
        val DEFAULT_FLAGS: Byte = (PARTICLES_FLAG or ICON_FLAG)

        private fun setFlag(current: Byte, mask: Byte, enabled: Boolean) =
            if (enabled) current or mask else current and mask.inv()

        private const val ID_TAG = "Id"
        private const val AMPLIFIER_TAG = "Amplifier"
        private const val DURATION_TAG = "Duration"
        private const val AMBIENT_TAG = "Ambient"
        private const val PARTICLES_TAG = "ShowParticles"
        private const val ICON_TAG = "ShowIcon"

        @JvmStatic
        fun load(data: CompoundTag): AquaPotionEffect {
            val type = AquaRegistries.POTION_TYPE.get(data.getByte(ID_TAG).toInt() - 1)!! // Potion type ID's in NBT start at 1, but registry indexes from 0
            val amplifier = (data.getByte(AMPLIFIER_TAG) + 1).toByte() // Amplifier levels in NBT start at 0, internal values start at 1
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
            putByte(ID_TAG, (AquaRegistries.POTION_TYPE.getId(effect.type.downcast()) + 1).toByte()) // Potion type ID's in NBT start at 1, but registry indexes from 0
            putByte(AMPLIFIER_TAG, (effect.amplifier - 1).toByte()) // Amplifier levels in NBT start at 0, internal values start at 1
            putInt(DURATION_TAG, effect.duration)
            putBoolean(AMBIENT_TAG, effect.ambient)
            putBoolean(PARTICLES_TAG, effect.particles)
            putBoolean(ICON_TAG, effect.icon)
        }
    }
}
