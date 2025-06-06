@file:JvmSynthetic
@file:Suppress("MatchingDeclarationName")
package net.aquamine.api.world.dimension

import net.aquamine.annotations.dsl.DimensionTypeDsl
import org.jetbrains.annotations.Contract

/**
 * Creates a new dimension type by applying the given [builder] to a new
 * dimension type builder and building the instance.
 *
 * @param builder The builder to apply.
 *
 * @return A new dimension type.
 */
@DimensionTypeDsl
@Contract("_ -> new", pure = true)
inline fun dimensionType(builder: DimensionType.Builder.() -> Unit): DimensionType = DimensionType.builder().apply(builder).build()
