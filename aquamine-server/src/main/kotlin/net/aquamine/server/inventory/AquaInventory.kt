package net.aquamine.server.inventory

import net.aquamine.api.inventory.Inventory
import net.aquamine.api.inventory.InventoryType
import net.aquamine.api.item.ItemStack
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.network.Writable
import net.aquamine.server.util.collection.FixedList

abstract class AquaInventory(
    val id: Int,
    final override val type: InventoryType,
    final override val size: Int,
    totalItems: Int = size
) : Inventory, Writable {

    private var stateId = 0
    final override val items = FixedList(totalItems, AquaItemStack.EMPTY)

    fun stateId(): Int = stateId

    fun incrementStateId(): Int {
        stateId = stateId + 1 and Short.MAX_VALUE.toInt()
        return stateId
    }

    final override fun getItem(index: Int): AquaItemStack = items[index]

    final override fun hasItem(item: ItemStack): Boolean = items.contains(item)

    final override fun addItem(item: ItemStack) {
        if (item !is AquaItemStack) return
        items.forEachIndexed { index, element ->
            if (element.type == item.type) {
                val initialAmount = element.amount
                val maxAmount = element.type.maximumStackSize
                if (initialAmount + item.amount <= maxAmount) {
                    items[index] = element.withAmount(initialAmount + item.amount)
                    return
                } else if (element.amount != maxAmount) {
                    items[index] = element.withAmount(maxAmount)
                    if (item.amount == 0) return
                }
            } else if (element.isEmpty()) {
                items[index] = item
                return
            }
        }
    }

    final override fun removeItem(item: ItemStack) {
        items.forEachIndexed { index, element ->
            if (element != item) return@forEachIndexed
            items[index] = AquaItemStack.EMPTY
            return
        }
    }

    final override fun clear() {
        items.forEachIndexed { index, _ -> items[index] = AquaItemStack.EMPTY }
    }

    final override fun iterator(): Iterator<AquaItemStack> = items.iterator()
}
