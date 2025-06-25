package net.aquamine.server.entity.components

import net.aquamine.api.entity.ArmorSlot
import net.aquamine.api.entity.Equipable
import net.aquamine.api.entity.EquipmentSlot
import net.aquamine.api.entity.Hand
import net.aquamine.api.item.ItemStack
import net.aquamine.server.item.KryptonItemStack
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.ListTag
import xyz.axie.nbt.list

interface KryptonEquipable : Equipable {

    fun setHeldItem(hand: Hand, item: KryptonItemStack)

    fun setArmor(slot: ArmorSlot, item: KryptonItemStack)

    fun setEquipment(slot: EquipmentSlot, item: KryptonItemStack)

    override fun setHeldItem(hand: Hand, item: ItemStack) {
        if (item !is KryptonItemStack) return
        setHeldItem(hand, item)
    }

    override fun setArmor(slot: ArmorSlot, item: ItemStack) {
        if (item !is KryptonItemStack) return
        setArmor(slot, item)
    }

    override fun setEquipment(slot: EquipmentSlot, item: ItemStack) {
        if (item !is KryptonItemStack) return
        setEquipment(slot, item)
    }

    companion object {

        @JvmStatic
        fun loadItems(tag: CompoundTag, name: String, output: MutableList<KryptonItemStack>) {
            if (!tag.contains(name, ListTag.ID)) return
            val items = tag.getList(name, ListTag.ID)
            for (i in 0 until items.size) {
                output[i] = KryptonItemStack.from(items.getCompound(i))
            }
        }

        @JvmStatic
        fun saveItems(items: Iterable<KryptonItemStack>): ListTag = list { items.forEach { if (!it.isEmpty()) add(it.save()) } }
    }
}
