package net.aquamine.server.inventory

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.aquamine.api.entity.ArmorSlot
import net.aquamine.api.entity.Hand
import net.aquamine.api.inventory.PlayerInventory
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.item.handler
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.out.play.PacketOutSetContainerSlot
import net.aquamine.server.util.collection.FixedList
import net.aquamine.server.world.block.state.AquaBlockState
import xyz.axie.nbt.ListTag
import xyz.axie.nbt.compound
import xyz.axie.nbt.list

class AquaPlayerInventory(override val owner: AquaPlayer) : AquaInventory(0, TYPE, SIZE, INVENTORY_SIZE), PlayerInventory {

    override val crafting: MutableList<AquaItemStack> = FixedList(CRAFTING_SIZE, AquaItemStack.EMPTY)
    override val armor: MutableList<AquaItemStack> = FixedList(ARMOR_SIZE, AquaItemStack.EMPTY)

    override val main: List<ItemStack> = items.subList(HOTBAR_SIZE, INVENTORY_SIZE - 1)
    override val hotbar: List<ItemStack> = items.subList(0, HOTBAR_SIZE - 1)

    override var helmet: ItemStack
        get() = getArmor(ArmorSlot.HELMET)
        set(value) = setArmor(ArmorSlot.HELMET, value)
    override var chestplate: ItemStack
        get() = getArmor(ArmorSlot.CHESTPLATE)
        set(value) = setArmor(ArmorSlot.CHESTPLATE, value)
    override var leggings: ItemStack
        get() = getArmor(ArmorSlot.LEGGINGS)
        set(value) = setArmor(ArmorSlot.LEGGINGS, value)
    override var boots: ItemStack
        get() = getArmor(ArmorSlot.BOOTS)
        set(value) = setArmor(ArmorSlot.BOOTS, value)

    override val mainHand: AquaItemStack
        get() = items.get(heldSlot)
    override var offHand: AquaItemStack = AquaItemStack.EMPTY

    override var heldSlot: Int = 0

    override fun getArmor(slot: ArmorSlot): AquaItemStack = armor.get(slot.ordinal)

    override fun setArmor(slot: ArmorSlot, item: ItemStack) {
        if (item !is AquaItemStack) return
        armor.set(slot.ordinal, item)
    }

    override fun getHeldItem(hand: Hand): AquaItemStack {
        if (hand == Hand.MAIN) return mainHand
        return offHand
    }

    override fun setHeldItem(hand: Hand, item: ItemStack) {
        if (item !is AquaItemStack) return
        when (hand) {
            Hand.MAIN -> setItem(heldSlot + INVENTORY_SIZE, item)
            Hand.OFF -> setItem(OFFHAND_SLOT, item)
        }
    }

    override fun setItem(index: Int, item: ItemStack) {
        if (item !is AquaItemStack) return
        setItem(index, item)
    }

    fun setItem(index: Int, item: AquaItemStack) {
        when (index) {
            0 -> crafting.set(CRAFTING_SLOT, item)
            in 1..CRAFTING_GRID_SIZE -> crafting.set(index - 1, item)
            in CRAFTING_SIZE until HOTBAR_SIZE -> armor.set(index - CRAFTING_SIZE, item)
            in HOTBAR_SIZE until INVENTORY_SIZE -> items.set(index, item)
            in INVENTORY_SIZE until OFFHAND_SLOT -> items.set(index - INVENTORY_SIZE, item)
            OFFHAND_SLOT -> offHand = item
        }
        owner.connection.send(PacketOutSetContainerSlot(id.toByte(), incrementStateId(), index.toShort(), item))
    }

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(SIZE)
        writer.writeItem(crafting.get(CRAFTING_SIZE - 1))
        for (i in 0 until CRAFTING_GRID_SIZE) {
            writer.writeItem(crafting.get(i))
        }
        armor.forEach(writer::writeItem)
        for (i in 0 until MAIN_SIZE) {
            writer.writeItem(items.get(i + HOTBAR_SIZE))
        }
        for (i in 0 until HOTBAR_SIZE) {
            writer.writeItem(items.get(i))
        }
        writer.writeItem(offHand)
    }

    fun getDestroySpeed(state: AquaBlockState): Float {
        val item = items.get(heldSlot)
        return item.type.handler().destroySpeed(item, state)
    }

    fun load(data: ListTag) {
        clear()
        data.forEachCompound {
            // No point creating the item stack just to throw it away if it's air
            if (it.getString("id") == ItemTypes.AIR.key().asString()) return@forEachCompound
            val slot = it.getByte("Slot").toInt()
            val stack = AquaItemStack.from(it)
            when (slot) {
                in items.indices -> items.set(slot, stack)
                BOOTS_DATA_SLOT -> armor.set(BOOTS_SLOT, stack)
                LEGGINGS_DATA_SLOT -> armor.set(LEGGINGS_SLOT, stack)
                CHESTPLATE_DATA_SLOT -> armor.set(CHESTPLATE_SLOT, stack)
                HELMET_DATA_SLOT -> armor.set(HELMET_SLOT, stack)
                OFFHAND_DATA_SLOT -> offHand = stack
            }
        }
    }

    fun save(): ListTag = list {
        items.forEachIndexed { index, item ->
            if (item.type === ItemTypes.AIR) return@forEachIndexed
            compound {
                putByte("Slot", index.toByte())
                item.save(this)
            }
        }
        var i: Byte = HELMET_DATA_SLOT.toByte()
        armor.forEach {
            if (it.type === ItemTypes.AIR) return@forEach
            compound {
                putByte("Slot", i--)
                it.save(this)
            }
        }
        if (offHand.type !== ItemTypes.AIR) {
            compound {
                putByte("Slot", OFFHAND_DATA_SLOT.toByte())
                offHand.save(this)
            }
        }
    }

    companion object {

        const val SIZE: Int = 46
        private const val MAIN_SIZE = 27
        private const val HOTBAR_SIZE = 9
        private const val INVENTORY_SIZE = MAIN_SIZE + HOTBAR_SIZE
        private const val CRAFTING_SIZE = 5
        private const val CRAFTING_GRID_SIZE = 4
        private const val CRAFTING_SLOT = 4
        private const val OFFHAND_SLOT = SIZE - 1
        private const val OFFHAND_DATA_SLOT = -106
        private const val ARMOR_SIZE = 4
        private const val HELMET_SLOT = 0
        private const val HELMET_DATA_SLOT = 103
        private const val CHESTPLATE_SLOT = 1
        private const val CHESTPLATE_DATA_SLOT = 102
        private const val LEGGINGS_SLOT = 2
        private const val LEGGINGS_DATA_SLOT = 101
        private const val BOOTS_SLOT = 3
        private const val BOOTS_DATA_SLOT = 100

        private val TYPE = AquaInventoryType(Key.key("aquamine", "inventory/player"), SIZE, Component.translatable("container.inventory"))
    }
}
