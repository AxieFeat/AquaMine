package net.aquamine.server.entity.util

import net.aquamine.api.entity.ArmorSlot
import net.aquamine.api.entity.EquipmentSlot

object EquipmentSlots {

    private val BY_ARMOR_SLOT = mapOf(
        ArmorSlot.BOOTS to EquipmentSlot.FEET,
        ArmorSlot.LEGGINGS to EquipmentSlot.LEGS,
        ArmorSlot.CHESTPLATE to EquipmentSlot.CHEST,
        ArmorSlot.HELMET to EquipmentSlot.HEAD
    )
    private val BY_NAME = EquipmentSlot.values().associateBy { it.name.lowercase() }

    @JvmStatic
    fun fromArmorSlot(slot: ArmorSlot): EquipmentSlot = BY_ARMOR_SLOT.get(slot)!!

    @JvmStatic
    fun fromName(name: String): EquipmentSlot? = BY_NAME.get(name)

    @JvmStatic
    fun index(slot: EquipmentSlot): Int = when (slot.type) {
        EquipmentSlot.Type.HAND -> slot.ordinal
        EquipmentSlot.Type.ARMOR -> slot.ordinal - 2
    }
}
