package net.aquamine.server.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import net.aquamine.server.util.nbt.SNBTParser
import xyz.axie.nbt.CompoundTag

/**
 * An argument type for parsing compound NBT tags from SNBT data.
 */
object NBTCompoundArgument : ArgumentType<CompoundTag> {

    private val EXAMPLES = listOf("{}", "{foo=bar}")

    override fun parse(reader: StringReader): CompoundTag = SNBTParser(reader).readCompound()

    override fun getExamples(): Collection<String> = EXAMPLES
}
