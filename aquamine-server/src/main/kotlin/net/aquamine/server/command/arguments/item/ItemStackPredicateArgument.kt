package net.aquamine.server.command.arguments.item

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType

/**
 * An argument type for parsing item stack predicates.
 */
object ItemStackPredicateArgument : ArgumentType<ItemStackPredicate> {

    private val EXAMPLES = listOf("minecraft:stone", "stone", "#minecraft:boats", "stone{foo=bar}")

    override fun getExamples(): Collection<String> = EXAMPLES

    override fun parse(reader: StringReader): ItemStackPredicate = ItemStackParser.parsePredicate(reader, true)
}
