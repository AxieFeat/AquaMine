package net.aquamine.api.entity.attribute

/**
 * An operation that can be applied to attribute modifiers.
 *
 * See [BasicModifierOperation] for the basic built-in operations.
 */
fun interface ModifierOperation {

    /**
     * Applies this operation to the given [base] value, modifying it with the
     * given [modifiers], and returns the result.
     *
     * @param base The base value to modify.
     * @param modifiers The modifiers to apply.
     *
     * @return The resulting modified value.
     */
    fun apply(base: Double, modifiers: Collection<AttributeModifier>): Double
}
