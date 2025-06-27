package net.aquamine.server.item.data

import net.kyori.adventure.key.Key
import net.aquamine.api.entity.EquipmentSlot
import net.aquamine.api.entity.attribute.AttributeType
import net.aquamine.api.entity.attribute.BasicModifierOperation
import net.aquamine.api.item.ItemAttributeModifier
import net.aquamine.server.entity.util.EquipmentSlots
import net.aquamine.server.entity.attribute.AquaAttributeModifier
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.util.nbt.getUUID
import net.aquamine.server.util.nbt.putUUID
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.compound
import java.util.UUID
import java.util.function.Supplier

class AquaItemAttributeModifier(
    override val type: AttributeType,
    override val slot: EquipmentSlot,
    uuid: UUID,
    nameGetter: Supplier<String>,
    amount: Double,
    override val operation: BasicModifierOperation
) : AquaAttributeModifier(uuid, nameGetter, amount, operation), ItemAttributeModifier {

    constructor(type: AttributeType, slot: EquipmentSlot, uuid: UUID, name: String, amount: Double,
                operation: BasicModifierOperation) : this(type, slot, uuid, { name }, amount, operation)

    override fun toString(): String = "ItemAttributeModifier(type=$type, slot=$slot, uuid=$uuid, name=$name, amount=$amount, operation=$operation)"

    object Factory : ItemAttributeModifier.Factory {

        override fun of(type: AttributeType, slot: EquipmentSlot, uuid: UUID, name: String, amount: Double,
                        operation: BasicModifierOperation): ItemAttributeModifier {
            return AquaItemAttributeModifier(type, slot, uuid, name, amount, operation)
        }
    }

    companion object {

        private const val ATTRIBUTE_NAME_TAG = "AttributeName"
        private const val SLOT_TAG = "Slot"

        @JvmStatic
        fun load(data: CompoundTag): AquaItemAttributeModifier? {
            val type = AquaRegistries.ATTRIBUTE.get(Key.key(data.getString(ATTRIBUTE_NAME_TAG))) ?: return null
            val slot = EquipmentSlots.fromName(data.getString(SLOT_TAG)) ?: return null
            return try {
                AquaItemAttributeModifier(type, slot, data.getUUID(UUID_TAG), data.getString(NAME_TAG), data.getDouble(AMOUNT_TAG),
                    getOperation(data))
            } catch (exception: Exception) {
                LOGGER.warn("Unable to create attribute!", exception)
                null
            }
        }

        @JvmStatic
        fun save(modifier: ItemAttributeModifier): CompoundTag = compound {
            putString(ATTRIBUTE_NAME_TAG, modifier.type.key().asString())
            putString(SLOT_TAG, modifier.slot.name)
            putUUID(UUID_TAG, modifier.uuid)
            putString(NAME_TAG, modifier.name)
            putDouble(AMOUNT_TAG, modifier.amount)
            putInt(OPERATION_TAG, modifier.operation.ordinal)
        }
    }
}
