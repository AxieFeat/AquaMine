package net.aquamine.server.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.aquamine.api.adventure.AdventureMessage

@JvmRecord
data class AquaAdventureMessage(private val component: Component) : AdventureMessage {

    override fun getString(): String = PlainTextComponentSerializer.plainText().serialize(component)

    override fun asComponent(): Component = component

    object Factory : AdventureMessage.Factory {

        override fun of(component: Component): AdventureMessage = AquaAdventureMessage(component)
    }
}
