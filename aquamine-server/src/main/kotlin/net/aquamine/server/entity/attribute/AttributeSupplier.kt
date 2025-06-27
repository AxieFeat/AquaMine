package net.aquamine.server.entity.attribute

import com.google.common.collect.ImmutableMap
import java.util.UUID
import java.util.function.Consumer

class AttributeSupplier(attributes: Map<AquaAttributeType, AquaAttribute>) {

    private val attributes = ImmutableMap.copyOf(attributes)

    fun hasAttribute(type: AquaAttributeType): Boolean = attributes.containsKey(type)

    fun hasModifier(type: AquaAttributeType, modifierId: UUID): Boolean = attributes.get(type)?.getModifier(modifierId) != null

    fun getValue(type: AquaAttributeType): Double = getAttribute(type).calculateValue()

    fun getBaseValue(type: AquaAttributeType): Double = getAttribute(type).baseValue

    fun getModifierValue(type: AquaAttributeType, modifierId: UUID): Double {
        val modifier = requireNotNull(getAttribute(type).getModifier(modifierId)) { "Cannot find modifier $modifierId on attribute ${type.key()}!" }
        return modifier.amount
    }

    fun create(type: AquaAttributeType, callback: Consumer<AquaAttribute>): AquaAttribute? {
        val attribute = attributes.get(type) ?: return null
        val copy = AquaAttribute(type, callback)
        copy.replaceFrom(attribute)
        return copy
    }

    private fun getAttribute(type: AquaAttributeType): AquaAttribute =
        requireNotNull(attributes.get(type)) { "Cannot find attribute ${type.key()}!" }

    class Builder {

        private val attributes = HashMap<AquaAttributeType, AquaAttribute>()
        private var frozen = false

        fun add(type: AquaAttributeType): Builder = apply { create(type) }

        fun add(type: AquaAttributeType, base: Double): Builder = apply { create(type).baseValue = base }

        fun build(): AttributeSupplier {
            frozen = true
            return AttributeSupplier(attributes)
        }

        private fun create(type: AquaAttributeType): AquaAttribute {
            val attribute = AquaAttribute(type) {
                if (frozen) throw UnsupportedOperationException("Attempted to change value for default attribute ${type.key()}!")
            }
            attributes.put(type, attribute)
            return attribute
        }
    }

    companion object {

        @JvmStatic
        fun builder(): Builder = Builder()
    }
}
