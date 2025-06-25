package net.aquamine.server.command.arguments.item

import net.aquamine.server.item.KryptonItemStack

fun interface ItemStackPredicate {

    fun test(item: KryptonItemStack): Boolean
}
