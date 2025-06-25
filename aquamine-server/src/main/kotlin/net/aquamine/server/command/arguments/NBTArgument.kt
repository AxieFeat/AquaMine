package net.aquamine.server.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import net.aquamine.server.util.nbt.SNBTParser
import xyz.axie.nbt.Tag

/**
 * An argument type for parsing generic NBT tags from SNBT data.
 */
object NBTArgument : ArgumentType<Tag> {

    private val EXAMPLES = listOf("0", "0b", "0l", "0.0", "\"foo\"", "{foo=bar}", "[0]")

    override fun parse(reader: StringReader): Tag = SNBTParser(reader).readValue()

    override fun getExamples(): Collection<String> = EXAMPLES
}
