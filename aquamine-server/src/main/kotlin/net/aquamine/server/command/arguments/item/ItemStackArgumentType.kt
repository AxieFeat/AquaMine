package net.aquamine.server.command.arguments.item

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import net.aquamine.server.command.CommandSourceStack

/**
 * An argument type that parses item stacks.
 */
object ItemStackArgumentType : ArgumentType<ItemStackArgument> {

    private val EXAMPLES = listOf("minecraft:cookie", "cookie", "cookie{foo=bar}")

    override fun getExamples(): Collection<String> = EXAMPLES

    override fun parse(reader: StringReader): ItemStackArgument = ItemStackParser.parseItem(reader)

    @JvmStatic
    fun get(context: CommandContext<CommandSourceStack>, name: String): ItemStackArgument = context.getArgument(name, ItemStackArgument::class.java)
}
