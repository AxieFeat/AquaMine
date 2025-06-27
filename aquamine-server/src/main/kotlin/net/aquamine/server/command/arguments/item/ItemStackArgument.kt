package net.aquamine.server.command.arguments.item

import kotlinx.collections.immutable.persistentListOf
import net.aquamine.server.item.ItemFactory
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.item.AquaItemType
import xyz.axie.nbt.CompoundTag

/**
 * An argument that represents an item, optionally with some NBT data.
 */
@JvmRecord
data class ItemStackArgument(val type: AquaItemType, val data: CompoundTag? = null) {

    /**
     * Creates the item stacks from the data stored by this argument.
     */
    fun createItemStacks(amount: Int): List<AquaItemStack> {
        if (amount <= type.maximumStackSize) return listOf(createStack(amount))
        val items = persistentListOf<AquaItemStack>().builder()
        var size = amount
        while (size > type.maximumStackSize) {
            items.add(createStack(type.maximumStackSize))
            size -= type.maximumStackSize
        }
        items.add(createStack(size))
        return items.build()
    }

    private fun createStack(amount: Int): AquaItemStack = AquaItemStack(type, amount, ItemFactory.create(type, data ?: CompoundTag.EMPTY))
}
