package net.aquamine.api.world.biome

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.aquamine.annotations.dsl.BiomeDsl
import net.kyori.adventure.builder.AbstractBuilder
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.particle.data.ParticleData

/**
 * Settings for ambient particles in biomes.
 */
@ImmutableType
interface AmbientParticleSettings {

    /**
     * The type of the particle for these settings.
     */
    val type: ParticleType

    /**
     * The data for the particle. This may be null, in which case either the
     * particle type has no associated particle data, or there is no data for
     * the particle in these settings.
     */
    val data: ParticleData?

    /**
     * The chance that particles of the above type will appear naturally in the
     * biome.
     */
    val probability: Float

    /**
     * A builder for ambient particle settings.
     *
     * Note: Attempting to call [Builder.build] on this without setting the
     * particle type will throw an [IllegalStateException].
     */
    @BiomeDsl
    interface Builder : AbstractBuilder<AmbientParticleSettings> {

        /**
         * Sets the type of the particle for the ambient particle settings to
         * the given [type].
         *
         * @param type The type.
         *
         * @return This builder.
         *
         * @see AmbientParticleSettings.type
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun type(type: ParticleType): Builder

        /**
         * Sets the particle data for the ambient particle settings to the
         * given [data].
         *
         * @param data The data.
         *
         * @return This builder.
         *
         * @see AmbientParticleSettings.data
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun data(data: ParticleData?): Builder

        /**
         * Sets the particle type and data for the ambient particle settings
         * to the given [type] and [data].
         *
         * @param type The type.
         * @param data The data.
         *
         * @return This builder.
         *
         * @see Builder.type
         * @see Builder.data
         */
        @BiomeDsl
        @Contract("_, _ -> this", mutates = "this")
        fun particle(type: ParticleType, data: ParticleData?): Builder = type(type).data(data)

        /**
         * Sets the probability for the ambient particle settings to the given
         * [probability].
         *
         * @param probability The probability.
         * @return This builder.
         *
         * @see AmbientParticleSettings.probability
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun probability(probability: Float): Builder
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(type: ParticleType, data: ParticleData?, probability: Float): AmbientParticleSettings

        fun builder(type: ParticleType): Builder
    }

    companion object {

        /**
         * Creates new ambient particle settings with the given values.
         *
         * @param type The type of particle.
         * @param data The data of the particle.
         * @param probability The probability.
         *
         * @return New ambient particle settings.
         */
        @JvmStatic
        @Contract("_, _, _ -> new", pure = true)
        fun of(type: ParticleType, data: ParticleData?, probability: Float): AmbientParticleSettings =
            AquaMine.factory<Factory>().of(type, data, probability)

        /**
         * Creates a new builder for ambient particle settings.
         *
         * @param type The particle type.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun builder(type: ParticleType): Builder = AquaMine.factory<Factory>().builder(type)
    }
}
