package net.aquamine.server.command.arguments.item

import kotlinx.collections.immutable.persistentListOf
import net.aquamine.server.item.ItemFactory
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.item.KryptonItemType
import xyz.axie.nbt.CompoundTag

/**
 * An argument that represents an item, optionally with some NBT data.
 */
@JvmRecord
data class ItemStackArgument(val type: KryptonItemType, val data: CompoundTag? = null) {

    /**
     * Creates the item stacks from the data stored by this argument.
     */
    fun createItemStacks(amount: Int): List<KryptonItemStack> {
        if (amount <= type.maximumStackSize) return listOf(createStack(amount))
        val items = persistentListOf<KryptonItemStack>().builder()
        var size = amount
        while (size > type.maximumStackSize) {
            items.add(createStack(type.maximumStackSize))
            size -= type.maximumStackSize
        }
        items.add(createStack(size))
        return items.build()
    }

    private fun createStack(amount: Int): KryptonItemStack = KryptonItemStack(type, amount, ItemFactory.create(type, data ?: CompoundTag.EMPTY))
}
