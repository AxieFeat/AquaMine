package net.aquamine.api.world.biome

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.aquamine.annotations.dsl.BiomeDsl
import net.kyori.adventure.builder.AbstractBuilder
import net.kyori.adventure.key.Keyed
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import java.util.function.Consumer

/**
 * A biome is a region in a world with distinct geographical features.
 */
@CataloguedBy(Biomes::class)
@ImmutableType
interface Biome : Keyed {

    /**
     * The climate of this biome.
     */
    val climate: Climate

    /**
     * The effects of this biome.
     */
    val effects: BiomeEffects

    /**
     * A builder for biomes.
     */
    @BiomeDsl
    interface Builder : AbstractBuilder<Biome> {

        /**
         * Sets the climate of the biome to the given [climate] and returns
         * this builder.
         *
         * @param climate The climate.
         *
         * @return This builder.
         *
         * @see Biome.climate
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun climate(climate: Climate): Builder

        /**
         * Applies the given [builder] to a new climate builder and sets the
         * climate to the built instance.
         *
         * @param builder The builder.
         *
         * @return This builder.
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun climate(builder: Consumer<Climate.Builder>): Builder =
            climate(Climate.builder().apply { builder.accept(this) }.build())

        /**
         * Sets the effect settings for the biome to the given [effects]
         * settings and returns this builder.
         *
         * @param effects The effect settings.
         *
         * @return This builder.
         *
         * @see Biome.effects
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun effects(effects: BiomeEffects): Builder

        /**
         * Applies the given [builder] to a new effects settings builder and
         * sets the effect settings to the built instance.
         *
         * @param builder The builder.
         *
         * @return This builder.
         */
        @BiomeDsl
        @Contract("_ -> this", mutates = "this")
        fun effects(builder: Consumer<BiomeEffects.Builder>): Builder =
            effects(BiomeEffects.builder().apply { builder.accept(this) }.build())
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun builder(): Builder
    }

    companion object {

        /**
         * Creates a new builder for building a new biome.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun builder(): Builder = AquaMine.factory<Factory>().builder()
    }
}