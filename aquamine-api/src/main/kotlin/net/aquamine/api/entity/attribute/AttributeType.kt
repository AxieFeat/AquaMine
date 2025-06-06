package net.aquamine.api.entity.attribute

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed
import net.kyori.adventure.translation.Translatable

/**
 * The type of attribute.
 */
@CataloguedBy(AttributeTypes::class)
@ImmutableType
interface AttributeType : Translatable, Keyed {

    /**
     * The default value for attributes of this type.
     */
    val defaultValue: Double

    /**
     * Ensures that the given [value] satisfies the constraints of this
     * attribute type.
     *
     * For example, with ranged attribute types, this will ensure that the
     * value is between the minimum and maximum value.
     *
     * @param value The value to sanitize.
     *
     * @return The sanitized result.
     */
    fun sanitizeValue(value: Double): Double
}
