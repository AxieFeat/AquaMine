package net.aquamine.api.item

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.entity.EquipmentSlot
import net.aquamine.api.entity.attribute.AttributeModifier
import net.aquamine.api.entity.attribute.AttributeType
import net.aquamine.api.entity.attribute.BasicModifierOperation
import java.util.UUID

/**
 * An attribute that can be applied to an item that modifies a specific
 * attribute on an entity that has the item this attribute is applied to
 * equip.
 */
@ImmutableType
interface ItemAttributeModifier : AttributeModifier {

    /**
     * The type of the attribute.
     */
    val type: AttributeType

    /**
     * The slot that the item has to be in for the attribute to apply.
     */
    val slot: EquipmentSlot

    override val operation: BasicModifierOperation

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(type: AttributeType, slot: EquipmentSlot, uuid: UUID, name: String, amount: Double,
               operation: BasicModifierOperation
        ): ItemAttributeModifier
    }

    companion object {

        /**
         * Creates a new item attribute modifier with the given [type], [slot],
         * [uuid], [name], [amount], [operation].
         *
         * @param type The type of the attribute.
         * @param slot The equipment slot.
         * @param uuid The UUID of the modifier.
         * @param name The name of the modifier.
         * @param amount The modifier amount.
         * @param operation The modifier operation.
         *
         * @return A new item attribute.
         */
        @JvmStatic
        @Contract("_, _, _ -> new", pure = true)
        fun of(type: AttributeType, slot: EquipmentSlot, uuid: UUID, name: String, amount: Double,
               operation: BasicModifierOperation
        ): ItemAttributeModifier {
            return AquaMine.factory<Factory>().of(type, slot, uuid, name, amount, operation)
        }

        /**
         * Creates a new item attribute modifier with the given [type], [slot],
         * and [modifier].
         *
         * @param type The type of the attribute.
         * @param slot The equipment slot.
         * @param modifier The modifier to take properties from.
         *
         * @return The resulting item attribute modifier.
         *
         * @throws IllegalArgumentException If the operation of the modifier is
         * not a [BasicModifierOperation].
         */
        @JvmStatic
        fun of(type: AttributeType, slot: EquipmentSlot, modifier: AttributeModifier): ItemAttributeModifier {
            require(modifier.operation is BasicModifierOperation) { "Modifier operation must be one of the basic operations!" }
            return of(type, slot, modifier.uuid, modifier.name, modifier.amount, modifier.operation as BasicModifierOperation)
        }
    }
}
