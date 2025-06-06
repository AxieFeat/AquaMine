package net.aquamine.api.world.biome

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.aquamine.annotations.dsl.BiomeDsl
import net.kyori.adventure.builder.AbstractBuilder
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine

/**
 * The climate for a biome.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
interface Climate {

    /**
     * The precipitation settings for this climate.
     */
    @get:JvmName("precipitation")
    val precipitation: Precipitation

    /**
     * The temperature of this climate.
     */
    @get:JvmName("temperature")
    val temperature: Float

    /**
     * The downfall of this climate.
     */
    @get:JvmName("downfall")
    val downfall: Float

    /**
     * The temperature modifier for this climate.
     */
    @get:JvmName("temperatureModifier")
    val temperatureModifier: TemperatureModifier

    /**
     * A builder for climates.
     */
    @BiomeDsl
    interface Builder : AbstractBuilder<Climate> {

        /**
         * Sets the precipitation for the climate to the given [precipitation]
         * and returns this builder.
         *
         * @param precipitation the precipitation
         * @return this builder
         * @see Climate.precipitation
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun precipitation(precipitation: Precipitation): Builder

        /**
         * Sets the temperature for the climate to the given [temperature] and
         * returns this builder.
         *
         * @param temperature the temperature
         * @return this builder
         * @see Climate.temperature
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun temperature(temperature: Float): Builder

        /**
         * Sets the downfall for the climate to the given [downfall] and
         * returns this builder.
         *
         * @param downfall the downfall
         * @return this builder
         * @see Climate.downfall
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun downfall(downfall: Float): Builder

        /**
         * Sets the temperature modifier for the climate to the given
         * [modifier] and returns this builder.
         *
         * @param modifier the temperature modifier
         * @return this builder
         * @see Climate.temperatureModifier
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun temperatureModifier(modifier: TemperatureModifier): Builder
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun builder(): Builder
    }

    companion object {

        /**
         * Creates a new climate with the given values.
         *
         * @param precipitation the precipitation
         * @param temperature the temperature
         * @param downfall the downfall
         * @param temperatureModifier the temperature modifier
         * @return a new climate
         */
        @JvmStatic
        @Contract("_, _, _, _ -> new", pure = true)
        fun of(precipitation: Precipitation, temperature: Float, downfall: Float, temperatureModifier: TemperatureModifier): Climate {
            return builder()
                .precipitation(precipitation)
                .temperature(temperature)
                .downfall(downfall)
                .temperatureModifier(temperatureModifier)
                .build()
        }

        /**
         * Creates a new builder for climates.
         *
         * @return a new builder
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = AquaMine.factory<Factory>().builder()
    }
}
