package net.aquamine.api.advancements

import net.aquamine.annotations.TypeFactory
import net.aquamine.annotations.dsl.AdvancementDsl
import net.aquamine.api.AquaMine
import net.aquamine.api.item.ItemStack
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.util.Buildable
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract

/**
 * Represents a root advancement located in an [AdvancementTab].
 */
interface AdvancementRoot : Advancement {

    /**
     * You should not override [root].
     * [AdvancementRoot] already root for all other advancements.
     */
    override var root: Advancement
        set(value) {}
        get() = this

    /**
     * Path to advancements background on a minecraft client.
     *
     * Example value: `minecraft:textures/gui/advancements/backgrounds/end.png`
     */
    var background: String

    @AdvancementDsl
    interface Builder : Buildable.Builder<AdvancementRoot> {

        /**
         * Sets the key of the [AdvancementRoot] being built.
         *
         * @param key The key of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun key(key: Key): Builder

        /**
         * Sets the title of the [AdvancementRoot] being built.
         *
         * @param component The title of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun title(component: Component): Builder

        /**
         * Sets the description of the [AdvancementRoot] being built.
         *
         * @param component The description of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun description(component: Component): Builder

        /**
         * Sets the icon of the [AdvancementRoot] being built.
         *
         * @param item The item for icon of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun icon(item: ItemStack): Builder

        /**
         * Sets the frame type of the [AdvancementRoot] being built.
         *
         * @param type The type frame of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun frame(type: FrameType): Builder

        /**
         * Sets the x position of the [AdvancementRoot] being built.
         *
         * @param x The x position of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun x(x: Float): Builder

        /**
         * Sets the y position of the [AdvancementRoot] being built.
         *
         * @param y The y position of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun y(x: Float): Builder

        /**
         * Sets the x and y position of the [AdvancementRoot] being built.
         *
         * @param x The x position of the advancement.
         * @param y The y position of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun xy(x: Float, y: Float): Builder = x(x).also { y(y) }

        /**
         * Sets the toast status of [AdvancementRoot] being built.
         *
         * @param toast The toast status.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun toast(toast: Boolean): Builder

        /**
         * Sets the hidden status of [AdvancementRoot] being built.
         *
         * @param toast The hidden status.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun hidden(hidden: Boolean): Builder

        /**
         * Sets the key of the [AdvancementRoot] being built.
         *
         * @param key The key of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun background(background: String): Builder
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun builder(): Builder

    }

    companion object {

        /**
         * Creates a new builder for building advancement.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder {
            return AquaMine.factory<Factory>().builder()
        }

        /**
         * Creates a new builder for building advancement.
         *
         * @param key The key of advancement.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(key: Key): Builder {
            return AquaMine.factory<Factory>().builder().key(key)
        }

    }

}