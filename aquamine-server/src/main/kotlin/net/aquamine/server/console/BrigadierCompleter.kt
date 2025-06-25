package net.aquamine.server.console

import org.apache.logging.log4j.LogManager
import org.jline.reader.Candidate
import org.jline.reader.Completer
import org.jline.reader.LineReader
import org.jline.reader.ParsedLine
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.KryptonCommandManager
import java.util.concurrent.ExecutionException
import java.util.function.Supplier

/**
 * Used for providing Brigadier completions for the console.
 */
class BrigadierCompleter(
    private val commandManager: KryptonCommandManager,
    private val commandSourceProvider: Supplier<CommandSourceStack>
) : Completer {

    override fun complete(reader: LineReader, line: ParsedLine, candidates: MutableList<Candidate>) {
        val input = line.line()
        val parseResults = commandManager.parse(commandSourceProvider.get(), input)
        val suggestions = commandManager.suggest(parseResults, line.cursor())

        try {
            suggestions.get().list.forEach {
                if (it.text.isEmpty()) return@forEach
                candidates.add(Candidate(it.text, it.text, null, it.tooltip?.string, null, null, parseResults.exceptions.isEmpty()))
            }
        } catch (exception: InterruptedException) {
            Thread.currentThread().interrupt()
        } catch (exception: ExecutionException) {
            LOGGER.error("Exception caught whilst trying to suggest completions for command $input!", exception)
        }
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}
