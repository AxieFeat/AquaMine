package net.aquamine.server.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

open class ComponentException(
    private val component: Component,
    cause: Throwable? = null
) : Exception(PlainTextComponentSerializer.plainText().serialize(component), cause), ComponentLike {

    override fun asComponent(): Component = component
}
