package net.aquamine.server.entity.attribute

import net.kyori.adventure.key.Key
import net.aquamine.api.entity.attribute.AttributeType
import net.aquamine.server.registry.AquaRegistries

open class AquaAttributeType protected constructor(
    final override val defaultValue: Double,
    val sendToClient: Boolean,
    private val translationKey: String
) : AttributeType {

    override fun key(): Key = AquaRegistries.ATTRIBUTE.getKey(this)!!

    override fun translationKey(): String = translationKey

    override fun sanitizeValue(value: Double): Double = value
}
