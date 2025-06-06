package net.aquamine.api.entity.attribute

import net.aquamine.api.registry.RegistryReference

/**
 * A holder of attributes.
 */
interface AttributeHolder {

    /**
     * Gets the attribute for the specified [type], or returns null if there
     * is no attribute for the given [type].
     *
     * @param type The type of the attribute.
     *
     * @return The attribute, or null if not present.
     */
    fun getAttribute(type: AttributeType): Attribute?

    /**
     * Gets the attribute for the specified [type], or returns null if there
     * is no attribute for the given [type].
     *
     * @param type The type of the attribute.
     *
     * @return The attribute, or null if not present.
     */
    fun getAttribute(type: RegistryReference<AttributeType>): Attribute? = getAttribute(type.get())
}
