package net.aquamine.server.network.chat

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.translation.Translatable
import net.aquamine.server.adventure.network.AdventureCodecs
import net.aquamine.server.util.ImmutableLists
import net.aquamine.server.util.serialization.EnumCodecs
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.codecs.RecordCodecBuilder

@JvmRecord
data class ChatTypeDecoration(private val translationKey: String, val parameters: List<Parameter>, val style: Style) : Translatable {

    override fun translationKey(): String = translationKey

    fun decorate(message: Component, type: RichChatType.Bound): Component =
        Component.translatable(translationKey, style, resolveParameters(message, type))

    private fun resolveParameters(message: Component, type: RichChatType.Bound): List<Component> =
        Array(parameters.size) { parameters.get(it).select(message, type) }.asList()

    enum class Parameter(private val selector: Selector) {

        SENDER({ _, type -> type.name }),
        TARGET({ _, type -> type.targetName }),
        CONTENT({ message, _ -> message });

        fun select(message: Component, type: RichChatType.Bound): Component = selector.select(message, type) ?: Component.empty()

        private fun interface Selector {

            fun select(message: Component, type: RichChatType.Bound): Component?
        }

        companion object {

            @JvmField
            val CODEC: Codec<Parameter> = EnumCodecs.of { Parameter.values() }
        }
    }

    companion object {

        @JvmField
        val CODEC: Codec<ChatTypeDecoration> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.fieldOf("translation_key").getting { it.translationKey() },
                Parameter.CODEC.listOf().fieldOf("parameters").getting { it.parameters },
                AdventureCodecs.STYLE_FORMATTING.optionalFieldOf("style", Style.empty()).getting { it.style }
            ).apply(instance, ::ChatTypeDecoration)
        }

        @JvmStatic
        fun withSender(key: String): ChatTypeDecoration =
            ChatTypeDecoration(key, ImmutableLists.of(Parameter.SENDER, Parameter.CONTENT), Style.empty())

        @JvmStatic
        fun incomingDirectMessage(key: String): ChatTypeDecoration {
            val style = Style.style().color(NamedTextColor.GRAY).decorate(TextDecoration.ITALIC).build()
            return ChatTypeDecoration(key, ImmutableLists.of(Parameter.SENDER, Parameter.CONTENT), style)
        }

        @JvmStatic
        fun outgoingDirectMessage(key: String): ChatTypeDecoration {
            val style = Style.style().color(NamedTextColor.GRAY).decorate(TextDecoration.ITALIC).build()
            return ChatTypeDecoration(key, ImmutableLists.of(Parameter.TARGET, Parameter.CONTENT), style)
        }

        @JvmStatic
        fun teamMessage(key: String): ChatTypeDecoration =
            ChatTypeDecoration(key, ImmutableLists.of(Parameter.TARGET, Parameter.SENDER, Parameter.CONTENT), Style.empty())
    }
}
