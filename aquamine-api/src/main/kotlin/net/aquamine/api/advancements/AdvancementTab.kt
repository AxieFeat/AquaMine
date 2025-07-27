package net.aquamine.api.advancements

import net.aquamine.annotations.TypeFactory
import net.aquamine.annotations.dsl.AdvancementDsl
import net.aquamine.api.AquaMine
import net.kyori.adventure.util.Buildable
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract

/**
 * Represents some tab with advancement in the advancement menu.
 */
interface AdvancementTab : Buildable<AdvancementTab, AdvancementTab.Builder> {

    /**
     * Path to advancements background on a minecraft client.
     *
     * Example value: `minecraft:textures/gui/advancements/backgrounds/end.png`
     */
    var background: String

    /**
     * All advancements in this tab.
     */
    var advancements: Advancement

    /**
     * For building new [AdvancementTab]s.
     */
    @AdvancementDsl
    interface Builder : Buildable.Builder<AdvancementTab> {

        /**
         * Sets the background of the [AdvancementTab] being built.
         *
         * @param background The background of the advancement tab.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun background(background: String): Builder

        /**
         * Sets the advancements of the [AdvancementTab] being built.
         *
         * @param advancements The advancements of the advancement tab.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun advancements(advancements: Advancement): Builder

    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun builder(): Builder

    }

    companion object {

        /**
         * Creates a new builder for building advancement tab.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder {
            return AquaMine.factory<Factory>().builder()
        }
    }

}
