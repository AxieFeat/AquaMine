package net.aquamine.server.pack.repository

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import java.util.function.UnaryOperator

interface PackSource {

    fun decorate(text: Component): Component

    fun shouldAddAutomatically(): Boolean

    companion object {

        @JvmField
        val DEFAULT: PackSource = create(UnaryOperator.identity(), true)
        @JvmField
        val BUILT_IN: PackSource = create(decorateWithSource("pack.source.builtin"), true)
        @JvmField
        val FEATURE: PackSource = create(decorateWithSource("pack.source.feature"), true)
        @JvmField
        val WORLD: PackSource = create(decorateWithSource("pack.source.world"), true)
        @JvmField
        val SERVER: PackSource = create(decorateWithSource("pack.source.server"), true)


        @JvmStatic
        fun create(function: UnaryOperator<Component>, addAutomatically: Boolean): PackSource = object : PackSource {
            override fun decorate(text: Component): Component = function.apply(text)

            override fun shouldAddAutomatically(): Boolean = addAutomatically
        }

        private fun decorateWithSource(key: String): UnaryOperator<Component> {
            val translation = Component.translatable(key)
            return UnaryOperator { Component.translatable("pack.nameAndSource", NamedTextColor.GRAY, it, translation) }
        }
    }
}
