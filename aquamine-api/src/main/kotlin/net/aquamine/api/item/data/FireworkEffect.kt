package net.aquamine.api.item.data

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.kyori.adventure.builder.AbstractBuilder
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.util.Color

/**
 * An effect that may be produced from a firework star exploding.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
interface FireworkEffect {

    /**
     * The type of this effect.
     */
    val type: FireworkEffectType

    /**
     * Whether this effect flickers.
     */
    @get:JvmName("hasFlicker")
    val hasFlicker: Boolean

    /**
     * Whether this effect has a trail.
     */
    @get:JvmName("hasTrail")
    val hasTrail: Boolean

    /**
     * The primary colors of this effect.
     */
    val colors: List<Color>

    /**
     * The fading colors of this effect.
     */
    val fadeColors: List<Color>

    /**
     * A builder for building firework effects.
     */
    interface Builder : AbstractBuilder<FireworkEffect> {

        /**
         * Makes the firework effect flicker.
         *
         * @return This builder.
         */
        @Contract("-> this", mutates = "this")
        fun flickers(): Builder

        /**
         * Makes the effect have a trail.
         *
         * @return This builder.
         */
        @Contract("-> this", mutates = "this")
        fun trail(): Builder

        /**
         * Adds the given [color] to the list of colors displayed by the
         * firework effect when it explodes.
         *
         * @param color The color to add.
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun addColor(color: Color): Builder

        /**
         * Adds the given [color] to the list of colors displayed by the
         * firework effect when it fades out after exploding.
         *
         * @param color The color to add.
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun addFadeColor(color: Color): Builder
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun builder(type: FireworkEffectType): Builder
    }

    companion object {

        /**
         * Creates a new builder for building a firework effect with the given
         * [type].
         *
         * @param type The type of the effect.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun builder(type: FireworkEffectType): Builder = AquaMine.factory<Factory>().builder(type)
    }
}
