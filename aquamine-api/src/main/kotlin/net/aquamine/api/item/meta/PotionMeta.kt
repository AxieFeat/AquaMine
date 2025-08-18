package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.dsl.MetaDsl
import net.aquamine.api.potion.PotionEffect
import net.aquamine.api.potion.PotionType
import net.aquamine.api.util.Color
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.Unmodifiable

/**
 * Item metadata for a potion effects.
 */
@ImmutableType
interface PotionMeta : ScopedItemMeta<PotionMeta.Builder, PotionMeta> {

    /**
     * The list of potion effects, that this potion has.
     */
    val effects: @Unmodifiable List<PotionEffect>

    /**
     * Base type of potion.
     * If you set some type, you should put effect with the same type to the [effects] list.
     */
    val basePotionType: PotionType?

    /**
     * Displayable color of potion.
     */
    val color: Color?

    /**
     * Creates new potion metadata with the given [effects].
     *
     * @param effects The effects.
     *
     * @return New potion metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withEffects(effects: List<PotionEffect>): PotionMeta

    /**
     * Creates new potion metadata with the given [effect] added to the list
     * of effects.
     *
     * @param effect The effect.
     *
     * @return New potion metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withEffect(effect: PotionEffect): PotionMeta

    /**
     * Creates new potion metadata with the effect at the given [index]
     * removed from the list of effects.
     *
     * @param index The index of the effect to remove.
     *
     * @return New potion metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withoutEffect(index: Int): PotionMeta

    /**
     * Creates new potion metadata with the given [effect] removed from the
     * list of effects.
     *
     * @param effect The effect to remove.
     *
     * @return New potion metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withoutEffect(effect: PotionEffect): PotionMeta

    /**
     * Creates new potion metadata with the given base potion [type].
     *
     * @param type The base potion effect type.
     *
     * @return New potion metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withBasePotionType(type: PotionType?): PotionMeta

    /**
     * Creates new potion metadata with the given [color].
     *
     * @param color The new color.
     *
     * @return New potion metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withColor(color: Color?): PotionMeta

    /**
     * A builder for building potion effect metadata.
     */
    @MetaDsl
    interface Builder : ItemMetaBuilder<Builder, PotionMeta> {

        /**
         * Sets the effects of the potion metadata.
         *
         * @param effects The effects.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun effects(effects: Collection<PotionEffect>): Builder

        /**
         * Sets the effects of the potion metadata.
         *
         * @param effects The effects.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun effects(vararg effects: PotionEffect): Builder = effects(effects.asList())

        /**
         * Adds the given [effect] to the list of effects for the potion
         * metadata.
         *
         * @param effect The effect to add.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun addEffect(effect: PotionEffect): Builder

        /**
         * Sets the base potion type of the potion metadata.
         *
         * @param basePotionType The base potion type.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun basePotionType(basePotionType: PotionType?): Builder

        /**
         * Sets the color of the potion metadata.
         *
         * @param color The color.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun color(color: Color?): Builder
    }

    companion object {

        /**
         * Creates a new builder for potion effect metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = ItemMeta.builder(PotionMeta::class.java)
    }
}
