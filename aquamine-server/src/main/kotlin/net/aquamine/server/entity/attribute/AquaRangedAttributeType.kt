package net.aquamine.server.entity.attribute

import net.aquamine.api.entity.attribute.RangedAttributeType
import net.aquamine.server.util.math.Maths

class AquaRangedAttributeType(
    defaultValue: Double,
    sendToClient: Boolean,
    translationKey: String,
    override val minimum: Double,
    override val maximum: Double
) : AquaAttributeType(defaultValue, sendToClient, translationKey), RangedAttributeType {

    init {
        require(minimum <= maximum) { "Minimum value cannot be larger than maximum value!" }
        require(defaultValue >= minimum) { "Default value cannot be less than minimum value!" }
        require(defaultValue <= maximum) { "Default value cannot be greater than maximum value!" }
    }

    override fun sanitizeValue(value: Double): Double = if (value.isNaN()) minimum else Maths.clamp(value, minimum, maximum)
}
