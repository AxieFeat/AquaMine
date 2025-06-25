package net.aquamine.server.adventure.network

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.aquamine.server.util.resultOrError
import org.kryptonmc.serialization.DataResult
import java.util.Locale

object TextColorSerialization {

    @JvmStatic
    fun encode(color: TextColor): String {
        if (color is NamedTextColor) return NamedTextColor.NAMES.key(color)!!
        return String.format(Locale.ROOT, "#%06X", color.value())
    }

    @JvmStatic
    fun decode(input: String): TextColor? = if (input.startsWith("#")) TextColor.fromHexString(input) else NamedTextColor.NAMES.value(input)

    @JvmStatic
    fun decodeResult(input: String): DataResult<TextColor> =
        decode(input).resultOrError { "Input string $input is not a valid named colour or hex colour!" }
}
