package net.aquamine.server.command.brigadier

import com.google.common.base.Splitter
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType

object StringArrayArgumentType : ArgumentType<Array<String>> {

    @JvmField
    val EMPTY: Array<String> = emptyArray()
    private val WORD_SPLITTER = Splitter.on(CommandDispatcher.ARGUMENT_SEPARATOR_CHAR)
    private val EXAMPLES = listOf("word", "some words")

    override fun parse(reader: StringReader): Array<String> {
        val text = reader.remaining
        reader.cursor = reader.totalLength
        if (text.isEmpty()) return EMPTY
        return WORD_SPLITTER.splitToList(text).toTypedArray()
    }

    override fun getExamples(): Collection<String> = EXAMPLES

    override fun toString(): String = "stringArray()"
}
