package net.aquamine.server.entity.attribute

import com.google.common.collect.Multimap
import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import net.aquamine.api.entity.attribute.AttributeModifier
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.util.map.nullableComputeIfAbsent
import xyz.axie.nbt.ListTag
import xyz.axie.nbt.list
import java.util.UUID

class AttributeMap(private val supplier: AttributeSupplier) {

    private val attributes = HashMap<AquaAttributeType, AquaAttribute>()
    private val dirty = HashSet<AquaAttribute>()

    fun syncable(): Collection<AquaAttribute> = attributes.values.filter { it.type.sendToClient }

    fun hasAttribute(type: AquaAttributeType): Boolean = attributes.get(type) != null || supplier.hasAttribute(type)

    fun hasModifier(type: AquaAttributeType, modifierId: UUID): Boolean {
        val attribute = attributes.get(type) ?: return supplier.hasModifier(type, modifierId)
        return attribute.getModifier(modifierId) != null
    }

    // Would use a method reference for onModified, but for whatever reason, using a method reference generates a kotlin.Function
    // implementation and then creates a Consumer that delegates to that.
    fun getAttribute(type: AquaAttributeType): AquaAttribute? =
        attributes.nullableComputeIfAbsent(type) { supplier.create(type) { onModified(it) } }

    fun getValue(type: AquaAttributeType): Double = attributes.get(type)?.calculateValue() ?: supplier.getValue(type)

    fun getBaseValue(type: AquaAttributeType): Double = attributes.get(type)?.baseValue ?: supplier.getBaseValue(type)

    fun getModifierValue(type: AquaAttributeType, modifierId: UUID): Double {
        val attribute = attributes.get(type) ?: return supplier.getModifierValue(type, modifierId)
        return requireNotNull(attribute.getModifier(modifierId)) { "Modifier $modifierId could not be found for attribute ${type.key()}!" }.amount
    }

    fun removeModifiers(modifiers: Multimap<AquaAttributeType, AttributeModifier>) {
        modifiers.asMap().forEach { (type, modifiers) ->
            val attribute = attributes.get(type)
            if (attribute != null) modifiers.forEach(attribute::removeModifier)
        }
    }

    fun addModifiers(modifiers: Multimap<AquaAttributeType, AttributeModifier>) {
        modifiers.forEach { type, modifier ->
            val attribute = getAttribute(type)
            if (attribute != null) {
                attribute.removeModifier(modifier)
                attribute.addModifier(modifier)
            }
        }
    }

    fun replaceFrom(other: AttributeMap) {
        other.attributes.values.forEach { getAttribute(it.type)?.replaceFrom(it) }
    }

    fun load(list: ListTag) {
        list.forEachCompound {
            val key = Key.key(it.getString("Name"))
            val type = AquaRegistries.ATTRIBUTE.get(key)
            if (type == null) {
                LOGGER.warn("Ignoring unknown attribute $key.")
                return@forEachCompound
            }
            getAttribute(type.downcast())?.load(it)
        }
    }

    fun save(): ListTag = list { attributes.values.forEach { add(it.save()) } }

    fun isDirty(): Boolean = dirty.isNotEmpty()

    fun dirty(): List<AquaAttribute> {
        val copy = ArrayList(dirty)
        dirty.clear()
        return copy
    }

    private fun onModified(attribute: AquaAttribute) {
        if (attribute.type.sendToClient) dirty.add(attribute)
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}
