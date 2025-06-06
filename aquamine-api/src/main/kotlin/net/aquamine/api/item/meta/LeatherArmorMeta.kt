package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.dsl.MetaDsl
import net.aquamine.api.util.Color
import org.jetbrains.annotations.Contract

/**
 * Item metadata for leather armor.
 */
@ImmutableType
interface LeatherArmorMeta : ScopedItemMeta<LeatherArmorMeta.Builder, LeatherArmorMeta> {

    /**
     * The displayed color of the item.
     */
    val color: Color?

    /**
     * Creates new item metadata with the given [color].
     *
     * @param color The new color.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withColor(color: Color?): LeatherArmorMeta

    /**
     * A builder for building leather armor metadata.
     */
    @MetaDsl
    interface Builder : ItemMetaBuilder<Builder, LeatherArmorMeta> {

        /**
         * Sets the color of the leather armor to the given [color].
         *
         * @param color The color.
         *
         * @return This builder
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun color(color: Color?): Builder
    }

    companion object {

        /**
         * Creates a new builder for building leather armor metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = ItemMeta.builder(LeatherArmorMeta::class.java)
    }
}
