package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.ImmutableTypeIgnore
import net.aquamine.annotations.dsl.MetaDsl
import net.kyori.adventure.inventory.Book
import net.kyori.adventure.text.Component
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.Unmodifiable
import net.aquamine.api.item.data.WrittenBookGeneration

/**
 * Item metadata for books that have been written.
 */
@Suppress("NonExtendableApiUsage")
@ImmutableType
interface WrittenBookMeta : BookMeta<WrittenBookMeta.Builder, WrittenBookMeta>, Book {

    /**
     * The title of the written book.
     */
    @get:JvmSynthetic
    @ImmutableTypeIgnore
    val title: Component

    /**
     * The author of the written book.
     */
    @get:JvmSynthetic
    @ImmutableTypeIgnore
    val author: Component

    /**
     * The generation of the written book.
     */
    val generation: WrittenBookGeneration

    @get:JvmSynthetic
    @ImmutableTypeIgnore
    override val pages: @Unmodifiable List<Component>

    /**
     * Creates new item metadata with the given [title].
     *
     * @param title The new title.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withTitle(title: Component): WrittenBookMeta

    /**
     * Creates new item metadata with the given [author].
     *
     * @param author The new author.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withAuthor(author: Component): WrittenBookMeta

    /**
     * Creates new item metadata with the given [generation].
     *
     * @param generation The new generation.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withGeneration(generation: WrittenBookGeneration): WrittenBookMeta

    override fun title(): Component = title

    override fun title(title: Component): WrittenBookMeta = withTitle(title)

    override fun author(): Component = author

    override fun author(author: Component): WrittenBookMeta = withAuthor(author)

    override fun pages(): List<Component> = pages

    override fun pages(pages: List<Component>): WrittenBookMeta = withPages(pages)

    override fun toBuilder(): Builder

    /**
     * A builder for building written book metadata.
     */
    @MetaDsl
    interface Builder : BookMeta.Builder<Builder, WrittenBookMeta>, Book.Builder {

        /**
         * Sets the generation of the book to the given [generation].
         *
         * @param generation The generation.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun generation(generation: WrittenBookGeneration): Builder

        @MetaDsl
        override fun title(title: Component): Builder

        @MetaDsl
        override fun author(author: Component): Builder

        @MetaDsl
        override fun addPage(page: Component): Builder

        @MetaDsl
        override fun pages(pages: Collection<Component>): Builder

        @MetaDsl
        override fun pages(vararg pages: Component): Builder = pages(pages.asList())
    }

    companion object {

        /**
         * Creates a new builder for building written book metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = ItemMeta.builder(WrittenBookMeta::class.java)
    }
}
