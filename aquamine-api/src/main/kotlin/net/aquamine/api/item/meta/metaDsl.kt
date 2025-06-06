@file:JvmSynthetic
package net.aquamine.api.item.meta

import net.aquamine.annotations.dsl.MetaDsl
import org.jetbrains.annotations.Contract

/**
 * Creates new item metadata from the result of applying the given [builder]
 * function.
 *
 * @param builder The builder.
 *
 * @return New item metadata.
 */
@MetaDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun itemMeta(builder: ItemMeta.Builder.() -> Unit): ItemMeta = ItemMeta.builder().apply(builder).build()

/**
 * Creates new item metadata of the given type [P] from the result of applying
 * the given [builder] function to a new builder of type [B].
 *
 * @param B The type of the builder.
 * @param P The type of the metadata.
 * @param builder The builder.
 *
 * @return New item metadata.
 */
@MetaDsl
@JvmSynthetic
@JvmName("itemMetaGeneric")
@Contract("_ -> new", pure = true)
inline fun <B : ItemMetaBuilder<B, P>, reified P : ItemMetaBuilder.Provider<B>> itemMeta(builder: B.() -> Unit): P =
    ItemMeta.builder(P::class.java).apply(builder).build()

/**
 * Creates new bundle metadata from the result of applying the given [builder]
 * function.
 *
 * @param builder The builder.
 *
 * @return New bundle metadata.
 */
@MetaDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun bundleMeta(builder: BundleMeta.Builder.() -> Unit): BundleMeta = BundleMeta.builder().apply(builder).build()

/**
 * Creates new compass metadata from the result of applying the given
 * [builder] function.
 *
 * @param builder The builder.
 *
 * @return New compass metadata.
 */
@MetaDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun compassMeta(builder: CompassMeta.Builder.() -> Unit): CompassMeta = CompassMeta.builder().apply(builder).build()

/**
 * Creates new crossbow metadata from the result of applying the given
 * [builder] function.
 *
 * @param builder The builder.
 *
 * @return New crossbow metadata.
 */
@MetaDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun crossbowMeta(builder: CrossbowMeta.Builder.() -> Unit): CrossbowMeta = CrossbowMeta.builder().apply(builder).build()

/**
 * Creates new firework rocket metadata from the result of applying the given
 * [builder] function.
 *
 * @param builder The builder.
 *
 * @return New firework rocket metadata.
 */
@MetaDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun fireworkRocketMeta(builder: FireworkRocketMeta.Builder.() -> Unit): FireworkRocketMeta =
    FireworkRocketMeta.builder().apply(builder).build()

/**
 * Creates new firework star metadata from the result of applying the given
 * [builder] function.
 *
 * @param builder The builder.
 *
 * @return New firework star metadata.
 */
@MetaDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun fireworkStarMeta(builder: FireworkStarMeta.Builder.() -> Unit): FireworkStarMeta =
    FireworkStarMeta.builder().apply(builder).build()

/**
 * Creates new leather armor metadata from the result of applying the given
 * [builder] function.
 *
 * @param builder The builder.
 *
 * @return New leather armor metadata.
 */
@MetaDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun leatherArmorMeta(builder: LeatherArmorMeta.Builder.() -> Unit): LeatherArmorMeta =
    LeatherArmorMeta.builder().apply(builder).build()

/**
 * Creates new player head metadata from the result of applying the given
 * [builder] function.
 *
 * @param builder The builder.
 *
 * @return New player head metadata.
 */
@MetaDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun playerHeadMeta(builder: PlayerHeadMeta.Builder.() -> Unit): PlayerHeadMeta = PlayerHeadMeta.builder().apply(builder).build()

/**
 * Creates new writable book metadata from the result of applying the given
 * [builder] function.
 *
 * @param builder The builder.
 *
 * @return New writable book metadata.
 */
@MetaDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun writableBookMeta(builder: WritableBookMeta.Builder.() -> Unit): WritableBookMeta =
    WritableBookMeta.builder().apply(builder).build()

/**
 * Creates new written book metadata from the result of applying the given
 * [builder] function.
 *
 * @param builder The builder.
 *
 * @return New written book metadata.
 */
@MetaDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun writtenBookMeta(builder: WrittenBookMeta.Builder.() -> Unit): WrittenBookMeta = WrittenBookMeta.builder().apply(builder).build()
