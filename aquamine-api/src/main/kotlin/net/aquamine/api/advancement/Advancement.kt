package net.aquamine.api.advancement

import net.aquamine.annotations.TypeFactory
import net.aquamine.annotations.dsl.AdvancementDsl
import net.aquamine.api.AquaMine
import net.aquamine.api.item.ItemStack
import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import net.kyori.adventure.text.Component
import net.kyori.adventure.util.Buildable
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract

/**
 * Represents an advancement located in an [AdvancementTab].
 */
interface Advancement : Buildable<Advancement, Advancement.Builder>, Keyed {

    /**
     * Root advancement. If not exist return current instance.
     */
    var root: Advancement

    /**
     * Title of this advancement.
     */
    var title: Component

    /**
     * Description of this advancement.
     */
    var description: Component

    /**
     * Icon of this advancement.
     */
    var icon: ItemStack

    /**
     * Frame type of this advancement.
     */
    var frameType: FrameType

    /**
     * X coordinate of this advancement in [AdvancementTab].
     */
    var x: Float

    /**
     * Y coordinate of this advancement in [AdvancementTab].
     */
    var y: Float

    /**
     * Should toast (the top right corner notification) be displayed?
     */
    var toast: Boolean

    /**
     * Is this advancement hidden?
     */
    var hidden: Boolean

    /**
     * For building new [Advancement]s.
     */
    @AdvancementDsl
    interface Builder : Buildable.Builder<Advancement> {

        /**
         * Sets the key of the [Advancement] being built.
         *
         * @param key The key of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun key(key: Key): Builder

        /**
         * Sets the root advancement of the [Advancement] being built.
         *
         * @param root The root advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun root(root: Advancement): Builder

        /**
         * Sets the title of the [Advancement] being built.
         *
         * @param component The title of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun title(component: Component): Builder

        /**
         * Sets the description of the [Advancement] being built.
         *
         * @param component The description of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun description(component: Component): Builder

        /**
         * Sets the icon of the [Advancement] being built.
         *
         * @param item The item for icon of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun icon(item: ItemStack): Builder

        /**
         * Sets the frame type of the [Advancement] being built.
         *
         * @param type The type frame of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun frame(type: FrameType): Builder

        /**
         * Sets the x position of the [Advancement] being built.
         *
         * @param x The x position of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun setX(x: Float): Builder

        /**
         * Sets the y position of the [Advancement] being built.
         *
         * @param y The y position of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun setY(y: Float): Builder

        /**
         * Sets the x and y position of the [Advancement] being built.
         *
         * @param x The x position of the advancement.
         * @param y The y position of the advancement.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun xy(x: Float, y: Float): Builder = setX(x).also { setY(y) }

        /**
         * Sets the toast status of [Advancement] being built.
         *
         * @param toast The toast status.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun toast(toast: Boolean): Builder

        /**
         * Sets the hidden status of [Advancement] being built.
         *
         * @param hidden The hidden status.
         *
         * @return This builder.
         */
        @AdvancementDsl
        @Contract("_ -> this", mutates = "this")
        fun hidden(hidden: Boolean): Builder
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

        /**
         * Creates a new builder for building advancement.
         *
         * @param key The key of advancement.
         * @param root The root advancement.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(key: Key, root: Advancement): Builder {
            return AquaMine.factory<Factory>().builder().key(key).root(root)
        }
    }
}
