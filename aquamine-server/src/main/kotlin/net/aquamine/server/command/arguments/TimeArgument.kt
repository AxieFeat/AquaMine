package net.aquamine.server.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import net.aquamine.server.command.CommandSourceStack
import kotlin.math.roundToInt

object TimeArgument : ArgumentType<Int> {

    private val EXAMPLES = setOf("0d", "0s", "0t", "0")
    private val ERROR_INVALID_UNIT = CommandExceptions.simple("argument.time.invalid_unit")
    private val ERROR_TICK_COUNT_TOO_LOW = CommandExceptions.simple("argument.time.invalid_tick_count")

    private val UNITS = mapOf(
        "d" to 24000,
        "s" to 20,
        "t" to 1,
        "" to 1
    )

    override fun parse(reader: StringReader): Int {
        val value = reader.readFloat()
        val unit = reader.readUnquotedString()
            ?: throw ERROR_INVALID_UNIT.createWithContext(reader)

        val multiplier = UNITS[unit]
            ?: throw ERROR_INVALID_UNIT.createWithContext(reader)

        val ticks = (value * multiplier).roundToInt()

        require(ticks >= 0) {
            throw ERROR_TICK_COUNT_TOO_LOW.createWithContext(reader)
        }

        return ticks
    }

    override fun getExamples(): Collection<String> = EXAMPLES

    @JvmStatic
    fun get(context: CommandContext<CommandSourceStack>, name: String): Int = context.getArgument(name, Int::class.java)
}
