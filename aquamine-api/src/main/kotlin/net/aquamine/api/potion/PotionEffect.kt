package net.aquamine.api.potion

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.aquamine.annotations.dsl.PotionDsl
import net.aquamine.api.AquaMine
import net.kyori.adventure.util.Buildable
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract

/**
 * A potion effect, that can be applied to [net.aquamine.api.entity.LivingEntity].
 */
@ImmutableType
interface PotionEffect : Buildable<PotionEffect, PotionEffect.Builder> {

    /**
     * Potion type of this effect.
     */
    val type: PotionType

    /**
     * The amplifier of this effect.
     * A higher amplifier means the potion effect happens more often over its duration and in some cases has more effect on its target.
     */
    val amplifier: Byte

    /**
     * The duration (in ticks) that this effect will run for when applied to a [net.aquamine.api.entity.LivingEntity].
     */
    val duration: Int

    /**
     * Is the effect spawned from a beacon?
     * Ambient effects use a different icon in the HUD (blue border rather than gray).
     */
    val ambient: Boolean

    /**
     * Is this potion effect should have particles?
     */
    val particles: Boolean

    /**
     * Is this potion effect should have icon in the HUD?
     */
    val icon: Boolean

    /**
     * Creates a new potion effect with the given [type].
     *
     * @param type The new type.
     *
     * @return A new potion effect.
     */
    @Contract("_ -> new", pure = true)
    fun withType(type: PotionType): PotionEffect

    /**
     * Creates a new potion effect with the given [amplifier].
     *
     * @param amplifier The new amplifier.
     *
     * @return A new potion effect.
     */
    @Contract("_ -> new", pure = true)
    fun withAmplifier(amplifier: Byte): PotionEffect

    /**
     * Grows the amplifier of this potion effect by the given [amplifier] and returns
     * the resulting potion effect.
     *
     * This will calculate the new amount by adding the given [amplifier] to the
     * current [PotionEffect.amplifier].
     *
     * @param amplifier The amount to grow by.
     *
     * @return The resulting potion effect.
     */
    @Contract("_ -> new", pure = true)
    fun growAmplifier(amplifier: Byte): PotionEffect = withAmplifier((this.amplifier + amplifier).toByte())

    /**
     * Shrinks the amplifier of this potion effect by the given [amplifier] and returns
     * the resulting potion effect.
     *
     * This will calculate the new amount by taking the given [amplifier] away
     * from the current [PotionEffect.amplifier].
     *
     * @param amplifier The amount to shrink by.
     *
     * @return The resulting potion effect.
     */
    @Contract("_ -> new", pure = true)
    fun shrinkAmplifier(amplifier: Byte): PotionEffect = withAmplifier((this.amplifier - amplifier).toByte())

    /**
     * Creates a new potion effect with the given [duration].
     *
     * @param duration The new duration.
     *
     * @return A new potion effect.
     */
    @Contract("_ -> new", pure = true)
    fun withDuration(duration: Int): PotionEffect

    /**
     * Grows the duration of this potion effect by the given [duration] and returns
     * the resulting potion effect.
     *
     * This will calculate the new amount by adding the given [duration] to the
     * current [PotionEffect.duration].
     *
     * @param duration The amount to grow by.
     *
     * @return The resulting potion effect.
     */
    @Contract("_ -> new", pure = true)
    fun growDuration(duration: Int): PotionEffect = withDuration(this.duration + duration)

    /**
     * Shrinks the duration of this potion effect by the given [duration] and returns
     * the resulting potion effect.
     *
     * This will calculate the new amount by taking the given [duration] away
     * from the current [PotionEffect.duration].
     *
     * @param duration The amount to shrink by.
     *
     * @return The resulting potion effect.
     */
    @Contract("_ -> new", pure = true)
    fun shrinkDuration(duration: Int): PotionEffect = withDuration(this.duration - duration)

    /**
     * Creates a new potion effect with the given [ambient] status.
     *
     * @param ambient The new ambient status.
     *
     * @return A new potion effect.
     */
    @Contract("_ -> new", pure = true)
    fun withAmbient(ambient: Boolean): PotionEffect

    /**
     * Creates a new potion effect without ambient.
     *
     * @return A new potion effect.
     */
    @Contract("-> new", pure = true)
    fun withoutAmbient(): PotionEffect = withAmbient(false)

    /**
     * Creates a new potion effect with the given [particles] status.
     *
     * @param particles The new particle's status.
     *
     * @return A new potion effect.
     */
    @Contract("_ -> new", pure = true)
    fun withParticles(particles: Boolean): PotionEffect

    /**
     * Creates a new potion effect without particles.
     *
     * @return A new potion effect.
     */
    @Contract("-> new", pure = true)
    fun withoutParticles(): PotionEffect = withParticles(false)

    /**
     * Creates a new potion effect with the given [icon] status.
     *
     * @param icon The new icon status.
     *
     * @return A new potion effect.
     */
    @Contract("_ -> new", pure = true)
    fun withIcon(icon: Boolean): PotionEffect

    /**
     * Creates a new potion effect without an icon.
     *
     * @return A new potion effect.
     */
    @Contract("-> new", pure = true)
    fun withoutIcon(): PotionEffect = withIcon(false)

    /**
     * For building new [PotionEffect]s.
     */
    @PotionDsl
    interface Builder : Buildable.Builder<PotionEffect> {

        /**
         * Sets the type of the [PotionEffect] being built.
         *
         * @param type The type of the potion effect.
         *
         * @return This builder.
         */
        @PotionDsl
        @Contract("_ -> this", mutates = "this")
        fun type(type: PotionType): Builder

        /**
         * Sets the amplifier of [PotionEffect] being built.
         *
         * @param amplifier The amplifier.
         *
         * @return This builder.
         */
        @PotionDsl
        @Contract("_ -> this", mutates = "this")
        fun amplifier(amplifier: Byte): Builder

        /**
         * Sets the duration of [PotionEffect] being built.
         *
         * @param duration The duration.
         *
         * @return This builder.
         */
        @PotionDsl
        @Contract("_ -> this", mutates = "this")
        fun duration(duration: Int): Builder

        /**
         * Sets the ambient status of [PotionEffect] being built.
         *
         * @param ambient The ambient status.
         *
         * @return This builder.
         */
        @PotionDsl
        @Contract("_ -> this", mutates = "this")
        fun ambient(ambient: Boolean): Builder

        /**
         * Sets the particles status of [PotionEffect] being built.
         *
         * @param particles The particles status.
         *
         * @return This builder.
         */
        @PotionDsl
        @Contract("_, _ -> this", mutates = "this")
        fun particles(particles: Boolean): Builder

        /**
         * Sets the icon status of [PotionEffect] being built.
         *
         * @param icon The icon status.
         *
         * @return This builder.
         */
        @PotionDsl
        @Contract("_, _ -> this", mutates = "this")
        fun icon(icon: Boolean): Builder

        /**
         * Builds a new [PotionEffect] with the settings retrieved from
         * this builder.
         */
        @Contract("_ -> new", pure = true)
        override fun build(): PotionEffect
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun builder(): Builder

    }

    companion object {

        /**
         * Creates a new builder for building a potion effect.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder {
            return AquaMine.factory<Factory>().builder()
        }

        /**
         * Creates a new potion effect with the given [type], [amplifier] and [duration].
         *
         * @param type The potion type.
         * @param amplifier The amplifier of potion effect.
         * @param duration The duration of potion effect.
         *
         * @return A new potion effect.
         */
        @JvmStatic
        @Contract("_, _, _ -> new", pure = true)
        fun of(type: PotionType, amplifier: Byte, duration: Int): PotionEffect {
            return AquaMine.factory<Factory>().builder()
                .type(type)
                .amplifier(amplifier)
                .duration(duration)
                .build()
        }
    }
}
