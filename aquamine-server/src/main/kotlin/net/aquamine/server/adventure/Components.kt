package net.aquamine.server.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import java.util.function.Function

object Components {

    private val DEFAULT_SEPARATOR = Component.text(", ", NamedTextColor.GRAY)

    @JvmStatic
    fun wrapInSquareBrackets(component: Component): Component = Component.translatable("chat.square_brackets", component)

    @JvmStatic
    fun <T> formatToList(values: Collection<T>, formattedGetter: Function<T, Component>): Component =
        formatToList(values, DEFAULT_SEPARATOR, formattedGetter)

    @JvmStatic
    fun <T> formatToList(values: Collection<T>, separator: Component, formattedGetter: Function<T, Component>): Component {
        if (values.isEmpty()) return Component.empty()
        if (values.size == 1) return formattedGetter.apply(values.first())

        // More than one element and we append to an empty component so styles aren't overridden by each other.
        val result = Component.text()
        var first = true
        values.forEach { value ->
            if (!first) result.append(separator)
            result.append(formattedGetter.apply(value))
            first = false
        }
        return result.build()
    }
}
