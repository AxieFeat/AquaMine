package net.aquamine.server.console

import org.apache.logging.log4j.LogManager
import org.jline.reader.Highlighter
import org.jline.reader.LineReader
import org.jline.utils.AttributedString
import org.jline.utils.AttributedStringBuilder
import org.jline.utils.AttributedStyle
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.KryptonCommandManager
import java.util.function.Supplier
import java.util.regex.Pattern
import java.util.stream.IntStream
import kotlin.math.min

/**
 * Used for doing console highlighting using Brigadier.
 */
class BrigadierHighlighter(
    private val commandManager: KryptonCommandManager,
    private val commandSourceProvider: Supplier<CommandSourceStack>
) : Highlighter {

    override fun highlight(lineReader: LineReader, buffer: String): AttributedString {
        return try {
            val results = commandManager.parse(commandSourceProvider.get(), buffer)
            val reader = results.reader
            val builder = AttributedStringBuilder()

            var lastPos = 0
            var argumentColorIndex = 0
            results.context.lastChild.nodes.forEach {
                val start = min(it.range.start, reader.totalLength)
                val end = min(it.range.end, reader.totalLength)
                if (lastPos < start) builder.append(reader.string, lastPos, start)
                builder.append(reader.string.substring(start, end), ARGUMENT_STYLES[argumentColorIndex])
                argumentColorIndex = (argumentColorIndex + 1) % ARGUMENT_STYLES.size
                lastPos = end
            }

            if (lastPos < reader.totalLength) {
                val style = if (results.exceptions.isEmpty()) LITERAL_STYLE else ERROR_STYLE
                builder.append(reader.string.substring(lastPos), style)
            }
            builder.toAttributedString()
        } catch (exception: Exception) {
            LOGGER.error("Exception caught whilst trying to highlight command $buffer!", exception)
            AttributedString(buffer)
        }
    }

    override fun setErrorPattern(errorPattern: Pattern?) {
        // do nothing
    }

    override fun setErrorIndex(errorIndex: Int) {
        // do nothing
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
        private val LITERAL_STYLE = AttributedStyle.DEFAULT
        private val ERROR_STYLE = LITERAL_STYLE.foreground(AttributedStyle.RED)
        private val ARGUMENT_STYLES = IntStream.of(
            AttributedStyle.CYAN,
            AttributedStyle.YELLOW,
            AttributedStyle.GREEN,
            AttributedStyle.MAGENTA,
            AttributedStyle.BLUE
        ).mapToObj { LITERAL_STYLE.foreground(it) }.toArray { arrayOfNulls<AttributedStyle>(it) }
    }
}
