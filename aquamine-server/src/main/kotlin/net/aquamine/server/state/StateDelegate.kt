package net.aquamine.server.state

import net.aquamine.api.state.Property
import net.aquamine.api.state.State
import net.aquamine.server.state.property.KryptonProperty
import net.aquamine.server.state.property.downcast

/**
 * This is a big hack that is used by state implementors to allow the
 * KryptonState generic to be a KryptonState instance whilst avoiding the clash
 * in supertype generic types.
 */
interface StateDelegate<S : State<S>, K : KryptonState<*, K>> : State<S> {

    override val availableProperties: Set<KryptonProperty<*>>
        get() = asState().values.keys
    override val properties: Map<Property<*>, Comparable<*>>
        @Suppress("UNCHECKED_CAST") get() = asState().values as Map<Property<*>, Comparable<*>>

    fun asState(): K

    override fun hasProperty(property: Property<*>): Boolean = asState().hasProperty(property.downcast())

    override fun <T : Comparable<T>> getProperty(property: Property<T>): T? = asState().getProperty(property.downcast())

    override fun <T : Comparable<T>> requireProperty(property: Property<T>): T = asState().requireProperty(property.downcast())

    @Suppress("UNCHECKED_CAST")
    override fun <T : Comparable<T>> setProperty(property: Property<T>, value: T): S = asState().setProperty(property.downcast(), value) as S
}
