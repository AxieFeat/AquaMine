package net.aquamine.server.state.property

import net.aquamine.api.state.Property
import net.aquamine.server.util.downcastApiType

fun <T : Comparable<T>> Property<T>.downcast(): AquaProperty<T> = downcastApiType("Property")
