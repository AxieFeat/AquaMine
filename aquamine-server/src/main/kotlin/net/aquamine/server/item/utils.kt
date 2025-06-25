package net.aquamine.server.item

import net.aquamine.api.item.ItemStack
import net.aquamine.server.item.handler.ItemHandler
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.data.ItemFlag
import net.aquamine.api.item.meta.ItemMeta
import net.aquamine.server.item.handler.DummyItemHandler
import net.aquamine.server.item.meta.AbstractItemMeta
import net.aquamine.server.util.downcastApiType

fun ItemStack.downcast(): KryptonItemStack = downcastApiType("ItemStack")

fun ItemType.downcast(): KryptonItemType = downcastApiType("ItemType")

fun ItemMeta.downcastBase(): AbstractItemMeta<*> = downcastApiType("ItemMeta")

fun ItemType.handler(): ItemHandler = ItemManager.handler(this) ?: DummyItemHandler

fun ItemFlag.mask(): Int = 1 shl ordinal
