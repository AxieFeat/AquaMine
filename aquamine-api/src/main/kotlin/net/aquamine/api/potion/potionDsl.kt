package net.aquamine.api.potion

import net.aquamine.annotations.dsl.PotionDsl
import org.jetbrains.annotations.Contract

/**
 * Creates a new potion effect from the result of applying the given [builder]
 * function.
 *
 * @param builder The builder.
 *
 * @return A new potion effect.
 */
@PotionDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun potionEffect(builder: PotionEffect.Builder.() -> Unit): PotionEffect = PotionEffect.builder().apply(builder).build()

/**
 * Creates a new potion effect with the given [type] and the result of applying
 * the given [builder] function.
 *
 * @param type The potion type.
 * @param builder The builder.
 *
 * @return A new potion effect.
 */
@PotionDsl
@JvmSynthetic
@Contract("_, _ -> new", pure = true)
inline fun potionEffect(type: PotionType, builder: PotionEffect.Builder.() -> Unit): PotionEffect =
    PotionEffect.builder().type(type).apply(builder).build()
