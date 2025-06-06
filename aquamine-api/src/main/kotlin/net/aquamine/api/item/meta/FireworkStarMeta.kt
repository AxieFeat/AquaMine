package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.dsl.MetaDsl
import org.jetbrains.annotations.Contract
import net.aquamine.api.item.data.FireworkEffect

/**
 * Item metadata for a firework star.
 */
@ImmutableType
interface FireworkStarMeta : ScopedItemMeta<FireworkStarMeta.Builder, FireworkStarMeta> {

    /**
     * The effect displayed when the star explodes.
     */
    val effect: FireworkEffect?

    /**
     * Creates new item metadata with the given [effect].
     *
     * @param effect The new effect.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withEffect(effect: FireworkEffect?): FireworkStarMeta

    /**
     * A builder for building firework star metadata.
     */
    @MetaDsl
    interface Builder : ItemMetaBuilder<Builder, FireworkStarMeta> {

        /**
         * Sets the effect of the star to the given [effect].
         *
         * @param effect The effect.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun effect(effect: FireworkEffect?): Builder
    }

    companion object {

        /**
         * Creates a new builder for building bundle metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = ItemMeta.builder(FireworkStarMeta::class.java)
    }
}
