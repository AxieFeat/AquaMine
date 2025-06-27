package net.aquamine.server.state.property

import net.aquamine.api.state.Property

object AquaPropertyFactory : Property.Factory {

    private var cachedProperties: Map<String, AquaProperty<*>>? = null

    @JvmStatic
    fun findByName(name: String): AquaProperty<*> = getAllProperties()[name]!!

    override fun forBoolean(name: String): Property<Boolean> = findByName(name) as BooleanProperty

    override fun forInt(name: String): Property<Int> = findByName(name) as IntProperty

    @Suppress("UNCHECKED_CAST")
    override fun <E : Enum<E>> forEnum(name: String): Property<E> = findByName(name) as EnumProperty<E>

    @JvmStatic
    private fun collectFieldProperties(): Map<String, AquaProperty<*>> {
        val map = HashMap<String, AquaProperty<*>>()
        AquaProperties::class.java.declaredFields.forEach { field ->
            try {
                val property = field.get(null)
                if (property is AquaProperty<*>) map.put(field.name, property)
            } catch (exception: IllegalAccessException) {
                exception.printStackTrace()
            }
        }
        return map
    }

    @JvmStatic
    private fun getAllProperties(): Map<String, AquaProperty<*>> {
        if (cachedProperties != null) return cachedProperties!!
        val builtins = collectFieldProperties()
        cachedProperties = builtins
        return builtins
    }
}
