@file:JvmSynthetic
package net.aquamine.api.item

import net.aquamine.annotations.dsl.ItemDsl
import org.jetbrains.annotations.Contract
import net.aquamine.api.item.meta.ItemMeta
import net.aquamine.api.item.meta.ItemMetaBuilder

/**
 * Creates a new item stack from the result of applying the given [builder]
 * function.
 *
 * @param builder The builder.
 *
 * @return A new item stack.
 */
@ItemDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun item(builder: ItemStack.Builder.() -> Unit): ItemStack = ItemStack.builder().apply(builder).build()

/**
 * Creates a new item stack with the given [type] and the result of applying
 * the given [builder] function.
 *
 * @param type The item type.
 * @param builder The builder.
 *
 * @return A new item stack.
 */
@ItemDsl
@JvmSynthetic
@Contract("_, _ -> new", pure = true)
inline fun item(type: ItemType, builder: ItemStack.Builder.() -> Unit): ItemStack = ItemStack.builder().type(type).apply(builder).build()

/**
 * Creates a new item stack with the given [type], [amount], and the result of
 * applying the given [builder] function.
 *
 * @param type The item type.
 * @param amount The number of items.
 * @param builder The builder.
 *
 * @return A new item stack.
 */
@ItemDsl
@JvmSynthetic
@Contract("_, _, _ -> new", pure = true)
inline fun item(type: ItemType, amount: Int, builder: ItemStack.Builder.() -> Unit): ItemStack =
    ItemStack.builder().type(type).amount(amount).apply(builder).build()

/**
 * Creates a new item stack with the given [type], [amount], and meta built
 * from the given [meta] builder.
 *
 * @param type The item type.
 * @param amount The number of items.
 * @param meta The meta builder.
 *
 * @return A new item stack.
 */
@ItemDsl
@JvmSynthetic
@JvmName("itemWithMeta")
@Contract("_, _, _ -> new", pure = true)
inline fun item(type: ItemType, amount: Int, meta: ItemMeta.Builder.() -> Unit): ItemStack =
    ItemStack.builder().type(type).amount(amount).meta(ItemMeta.builder().apply(meta).build()).build()

/**
 * Creates a new item stack with the given [type], [amount], and meta of the
 * given type [P] built from the given [meta] builder of the given type [B].
 *
 * @param B The type of the metadata builder.
 * @param P The type of the metadata.
 * @param type The item type.
 * @param amount The number of items.
 * @param meta The meta builder.
 *
 * @return A new item stack.
 */
@ItemDsl
@JvmSynthetic
@JvmName("itemWithMetaGeneric")
@Contract("_, _, _ -> new", pure = true)
inline fun <B : ItemMetaBuilder<B, P>, reified P> item(type: ItemType, amount: Int, meta: B.() -> Unit): ItemStack
where P : ItemMetaBuilder.Provider<B>, P : ItemMeta {
    return ItemStack.builder().type(type).amount(amount).meta(meta).build()
}
