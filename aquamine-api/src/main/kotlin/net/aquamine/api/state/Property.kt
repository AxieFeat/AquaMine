@file:JvmSynthetic
package net.aquamine.api.state

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Unmodifiable

/**
 * Represents a property key.
 */
@CataloguedBy(Properties::class)
@ImmutableType
interface Property<T : Comparable<T>> {

    /**
     * The name of the property key.
     */
    val name: String

    /**
     * The type of this property key.
     */
    val type: Class<T>

    /**
     * The set of values this property key allows.
     */
    val values: @Unmodifiable Collection<T>

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun forBoolean(name: String): Property<Boolean>

        fun forInt(name: String): Property<Int>

        fun <E : Enum<E>> forEnum(name: String): Property<E>
    }
}
