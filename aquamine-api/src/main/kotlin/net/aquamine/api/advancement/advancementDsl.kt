package net.aquamine.api.advancement

import net.aquamine.annotations.dsl.AdvancementDsl
import net.kyori.adventure.key.Key
import org.jetbrains.annotations.Contract

/**
 * Creates new advancement from the result of applying the given [builder]
 * function.
 *
 * @param builder The builder.
 *
 * @return New advancement.
 */
@AdvancementDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun advancement(builder: Advancement.Builder.() -> Unit): Advancement = Advancement.builder().apply(builder).build()

/**
 * Creates new advancement with the given [key] and the result of applying
 * the given [builder] function.
 *
 * @param key Key of advancement.
 * @param builder The builder.
 *
 * @return New advancement.
 */
@AdvancementDsl
@JvmSynthetic
@Contract("_, _ -> new", pure = true)
inline fun advancement(key: Key, builder: Advancement.Builder.() -> Unit): Advancement = Advancement.builder().key(key).apply(builder).build()

/**
 * Creates new advancement with the given [key], [root] and the result of applying
 * the given [builder] function.
 *
 * @param key Key of advancement.
 * @param root Root advancement.
 * @param builder The builder.
 *
 * @return New advancement.
 */
@AdvancementDsl
@JvmSynthetic
@Contract("_, _ -> new", pure = true)
inline fun advancement(key: Key, root: Advancement, builder: Advancement.Builder.() -> Unit): Advancement =
    Advancement.builder().key(key).root(root).apply(builder).build()
