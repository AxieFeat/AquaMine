package net.aquamine.server.command.arguments.item

import net.aquamine.server.item.AquaItemStack

fun interface ItemStackPredicate {

    fun test(item: AquaItemStack): Boolean
}
