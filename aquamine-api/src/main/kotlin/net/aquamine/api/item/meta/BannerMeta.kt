package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.dsl.MetaDsl
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.Unmodifiable
import net.aquamine.api.block.entity.banner.BannerPattern

/**
 * Item metadata for a banner.
 */
@ImmutableType
interface BannerMeta : ScopedItemMeta<BannerMeta.Builder, BannerMeta> {

    /**
     * All the patterns for this banner metadata.
     */
    val patterns: @Unmodifiable List<BannerPattern>

    /**
     * Creates new banner metadata with the given [patterns].
     *
     * @param patterns The patterns.
     *
     * @return New banner metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withPatterns(patterns: List<BannerPattern>): BannerMeta

    /**
     * Creates new banner metadata with the given [pattern] added to the list
     * of patterns.
     *
     * @param pattern The pattern to add.
     *
     * @return New banner metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withPattern(pattern: BannerPattern): BannerMeta

    /**
     * Creates new banner metadata with the pattern at the given [index]
     * removed from the list of patterns.
     *
     * @param index The index of the pattern to remove.
     *
     * @return New banner metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withoutPattern(index: Int): BannerMeta

    /**
     * Creates new banner metadata with the given [pattern] removed from the
     * list of patterns.
     *
     * @param pattern The pattern to remove.
     *
     * @return New banner metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withoutPattern(pattern: BannerPattern): BannerMeta

    /**
     * A builder for building banner metadata.
     */
    @MetaDsl
    interface Builder : ItemMetaBuilder<Builder, BannerMeta> {

        /**
         * Sets the patterns of the banner metadata.
         *
         * @param patterns The patterns.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun patterns(patterns: Collection<BannerPattern>): Builder

        /**
         * Sets the patterns of the banner metadata.
         *
         * @param patterns The patterns.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun patterns(vararg patterns: BannerPattern): Builder = patterns(patterns.asList())

        /**
         * Adds the given [pattern] to the list of patterns for the banner
         * metadata.
         *
         * @param pattern The pattern to add.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun addPattern(pattern: BannerPattern): Builder
    }

    companion object {

        /**
         * Creates a new builder for building banner metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = ItemMeta.builder(BannerMeta::class.java)
    }
}
