package net.aquamine.server.adventure.network

import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.format.TextDecoration.State as DecorationState
import net.aquamine.server.util.Keys
import net.aquamine.server.util.OptionalBoolean
import net.aquamine.serialization.Codec
import net.aquamine.serialization.MapCodec
import net.aquamine.serialization.codecs.RecordCodecBuilder
import java.util.Optional

object AdventureCodecs {

    @JvmField
    val TEXT_COLOR: Codec<TextColor> = Codec.STRING.comapFlatMap({ TextColorSerialization.decodeResult(it) }, { TextColorSerialization.encode(it) })
    @JvmField
    val STYLE_FORMATTING: Codec<Style> = RecordCodecBuilder.create { instance ->
        instance.group(
            TEXT_COLOR.optionalFieldOf("color").getting { Optional.ofNullable(it.color()) },
            decorationStateCodec("bold").getting { it.decoration(TextDecoration.BOLD) },
            decorationStateCodec("italic").getting { it.decoration(TextDecoration.ITALIC) },
            decorationStateCodec("underlined").getting { it.decoration(TextDecoration.UNDERLINED) },
            decorationStateCodec("strikethrough").getting { it.decoration(TextDecoration.STRIKETHROUGH) },
            decorationStateCodec("obfuscated").getting { it.decoration(TextDecoration.OBFUSCATED) },
            Codec.STRING.optionalFieldOf("insertion").getting { Optional.ofNullable(it.insertion()) },
            Keys.CODEC.optionalFieldOf("font").getting { Optional.ofNullable(it.font()) }
        ).apply(instance) { color, bold, italic, underlined, strikethrough, obfuscated, insertion, font ->
            Style.style()
                .color(color.orElse(null))
                .decoration(TextDecoration.BOLD, bold)
                .decoration(TextDecoration.ITALIC, italic)
                .decoration(TextDecoration.UNDERLINED, underlined)
                .decoration(TextDecoration.STRIKETHROUGH, strikethrough)
                .decoration(TextDecoration.OBFUSCATED, obfuscated)
                .insertion(insertion.orElse(null))
                .font(font.orElse(null))
                .build()
        }
    }

    @JvmStatic
    private fun decorationStateCodec(name: String): MapCodec<DecorationState> = Codec.BOOLEAN.optionalFieldOf(name)
        .xmap({ it.map(DecorationState::byBoolean).orElse(DecorationState.NOT_SET) }, ::stateAsBoolean)

    @JvmStatic
    private fun stateAsBoolean(state: DecorationState): Optional<Boolean> = when (state) {
        DecorationState.NOT_SET -> Optional.empty()
        DecorationState.TRUE -> OptionalBoolean.TRUE
        DecorationState.FALSE -> OptionalBoolean.FALSE
    }
}
