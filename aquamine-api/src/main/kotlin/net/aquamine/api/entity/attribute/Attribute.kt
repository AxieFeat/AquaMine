package net.aquamine.api.entity.attribute

import java.util.UUID

/**
 * Represents an attribute that can be applied to a living entity.
 */
interface Attribute {

    /**
     * The type of this attribute.
     */
    val type: AttributeType

    /**
     * The base value of this attribute.
     */
    var baseValue: Double

    /**
     * The modifiers used to modify the [baseValue] in calculation to get the
     * value.
     */
    val modifiers: Collection<AttributeModifier>

    /**
     * Calculates the final value of this attribute by applying the modifiers
     * to the base value.
     *
     * @return The final value of this attribute.
     */
    fun calculateValue(): Double

    /**
     * Gets the modifier with the given [uuid], or returns null if there is no
     * modifier with the given [uuid].
     *
     * @param uuid The UUID.
     *
     * @return The modifier, or null if not present.
     */
    fun getModifier(uuid: UUID): AttributeModifier?

    /**
     * Gets all modifiers stored under the given [operation].
     *
     * @param operation The operation.
     *
     * @return All modifiers for the given operation.
     */
    fun getModifiers(operation: ModifierOperation): Set<AttributeModifier>

    /**
     * Adds the given [modifier] to the list of modifiers.
     *
     * @param modifier The modifier to add.
     */
    fun addModifier(modifier: AttributeModifier)

    /**
     * Removes the given [modifier] from the list of modifiers.
     *
     * @param modifier The modifier to remove.
     */
    fun removeModifier(modifier: AttributeModifier)

    /**
     * Clears all modifiers for this attribute.
     */
    fun clearModifiers()
}
