package net.aquamine.api.state

import net.aquamine.annotations.ImmutableType
import org.jetbrains.annotations.Unmodifiable

/**
 * Something that is a possible state of something else.
 */
@ImmutableType
interface State<out S : State<S>> {

    /**
     * All the properties that can be set in this state.
     */
    val availableProperties: @Unmodifiable Set<Property<*>>

    /**
     * All the properties that are currently set in this state.
     */
    val properties: @Unmodifiable Map<Property<*>, Comparable<*>>

    /**
     * Checks if the given [property] has a value on this state.
     *
     * @param property The property to check.
     *
     * @return `true` if this state has a value for the given property, `false`
     * otherwise.
     */
    fun hasProperty(property: Property<*>): Boolean

    /**
     * Gets the value for the given [property], or returns null if there is no
     * value for the given [property].
     *
     * @param T The property type.
     * @param property The property.
     *
     * @return The value, or null if not present.
     */
    fun <T : Comparable<T>> getProperty(property: Property<T>): T?

    /**
     * Gets the value for the given [property], or throws an
     * [IllegalArgumentException] if there is no value for the given
     * [property].
     *
     * @param T The property type.
     * @param property The property.
     * @return The value.
     *
     * @throws IllegalArgumentException If there is no value for the property.
     */
    fun <T : Comparable<T>> requireProperty(property: Property<T>): T

    /**
     * Sets the value for the given [property] to the given [value] and returns
     * the resulting state.
     *
     * As states are immutable, this will return a different state where the
     * given [property] has the given [value].
     *
     * @param T The property type.
     * @param property The property.
     * @param value The value.
     *
     * @return The state with the property set to the value.
     */
    fun <T : Comparable<T>> setProperty(property: Property<T>, value: T): S
}
