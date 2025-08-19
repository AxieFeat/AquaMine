package net.aquamine.server.potion

import net.aquamine.api.entity.attribute.ModifierOperation
import net.aquamine.server.entity.attribute.AquaAttributeModifier
import java.util.UUID

class PotionAttributeTemplate(
    name: String,
    private val amount: Double,
    private val operation: ModifierOperation
) {

    private val effectName = "effect.$name"

    val uuid: UUID = UUID.nameUUIDFromBytes(effectName.toByteArray())

    fun create(amplifier: Byte): AquaAttributeModifier {
        return AquaAttributeModifier(
            uuid = uuid,
            name = effectName,
            amount = amount * amplifier,
            operation = operation
        )
    }
}
