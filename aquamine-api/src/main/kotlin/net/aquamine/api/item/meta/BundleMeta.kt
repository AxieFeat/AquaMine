package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.dsl.MetaDsl
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.Unmodifiable
import net.aquamine.api.item.ItemStack

/**
 * Item metadata for a bundle.
 */
@ImmutableType
interface BundleMeta : ScopedItemMeta<BundleMeta.Builder, BundleMeta> {

    /**
     * The items contained within the bundle.
     */
    val items: @Unmodifiable List<ItemStack>

    /**
     * Creates new item metadata with the given [items].
     *
     * @param items The new items.
     *
     * @return The new metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withItems(items: List<ItemStack>): BundleMeta

    /**
     * Creates new item metadata with the given [item] added to the end of the
     * item list.
     *
     * @param item The item to add.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withItem(item: ItemStack): BundleMeta

    /**
     * Creates new item metadata with the item at the given [index] removed
     * from the item list.
     *
     * @param index The index of the item to remove.
     *
     * @return New item metadata.
     *
     * @throws IllegalArgumentException if the index results in an out of
     * bounds exception, i.e., when it is too small or too big.
     */
    @Contract("_ -> new", pure = true)
    fun withoutItem(index: Int): BundleMeta

    /**
     * Creates new item metadata with the given [item] removed from the item
     * list.
     *
     * @param item The item to remove.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withoutItem(item: ItemStack): BundleMeta

    /**
     * A builder for building bundle metadata.
     */
    @MetaDsl
    interface Builder : ItemMetaBuilder<Builder, BundleMeta> {

        /**
         * Sets the items held by the bundle to the given [items].
         *
         * @param items The items.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun items(items: Collection<ItemStack>): Builder

        /**
         * Sets the items held by the bundle to the given [items].
         *
         * @param items The items.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun items(vararg items: ItemStack): Builder = items(items.asList())

        /**
         * Adds the given [item] to the bundle.
         *
         * @param item The item to add.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun addItem(item: ItemStack): Builder
    }

    companion object {

        /**
         * Creates a new builder for building bundle metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = ItemMeta.builder(BundleMeta::class.java)
    }
}
