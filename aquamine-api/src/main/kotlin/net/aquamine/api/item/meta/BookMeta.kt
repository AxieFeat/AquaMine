package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.dsl.MetaDsl
import net.kyori.adventure.text.Component
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.Unmodifiable

/**
 * Contains shared metadata between [WritableBookMeta] and [WrittenBookMeta].
 */
@ImmutableType
sealed interface BookMeta<B : BookMeta.Builder<B, I>, I : BookMeta<B, I>> : ScopedItemMeta<B, I> {

    /**
     * The pages written in the book.
     */
    val pages: @Unmodifiable List<Component>

    /**
     * Creates new item metadata with the given [pages].
     *
     * @param pages The new pages.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withPages(pages: Collection<Component>): I

    /**
     * Creates new item metadata with the given [page] added to the end of the
     * pages.
     *
     * @param page The page to add.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withPage(page: Component): I

    /**
     * Creates new item metadata with the page at the given [index] removed
     * from the pages.
     *
     * @param index The index of the page to remove.
     *
     * @return New item metadata.
     *
     * @throws IllegalArgumentException If the index results in an out of
     * bounds exception, i.e., when it is too small or too big.
     */
    @Contract("_ -> new", pure = true)
    fun withoutPage(index: Int): I

    /**
     * Creates new item metadata with the given [page] removed from the pages.
     *
     * @param page The page to remove.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withoutPage(page: Component): I

    /**
     * A builder for building book metadata.
     */
    @MetaDsl
    interface Builder<B : Builder<B, I>, I : BookMeta<B, I>> : ItemMetaBuilder<B, I> {

        /**
         * Sets the pages the book has to the given [pages].
         *
         * @param pages The pages.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun pages(pages: Collection<Component>): B

        /**
         * Sets the pages the book has to the given [pages].
         *
         * @param pages The pages.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun pages(vararg pages: Component): B = pages(pages.asList())

        /**
         * Adds the given [page] to the list of pages the book has.
         *
         * @param page The page to add.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun addPage(page: Component): B
    }
}
