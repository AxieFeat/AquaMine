package net.aquamine.api.entity.attribute

import net.aquamine.annotations.ImmutableType

/**
 * A type of attribute that only accepts values between a minimum and maximum
 * value.
 */
@ImmutableType
interface RangedAttributeType : AttributeType {

    /**
     * The minimum value for attributes of this type.
     */
    val minimum: Double

    /**
     * The maximum value for attributes of this type.
     */
    val maximum: Double
}
