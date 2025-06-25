package net.aquamine.server.state.property

import net.aquamine.api.state.Property

object KryptonPropertyFactory : Property.Factory {

    private var cachedProperties: Map<String, KryptonProperty<*>>? = null

    @JvmStatic
    fun findByName(name: String): KryptonProperty<*> = getAllProperties()[name]!!

    override fun forBoolean(name: String): Property<Boolean> = findByName(name) as BooleanProperty

    override fun forInt(name: String): Property<Int> = findByName(name) as IntProperty

    @Suppress("UNCHECKED_CAST")
    override fun <E : Enum<E>> forEnum(name: String): Property<E> = findByName(name) as EnumProperty<E>

    @JvmStatic
    private fun collectFieldProperties(): Map<String, KryptonProperty<*>> {
        val map = HashMap<String, KryptonProperty<*>>()
        KryptonProperties::class.java.declaredFields.forEach { field ->
            try {
                val property = field.get(null)
                if (property is KryptonProperty<*>) map.put(field.name, property)
            } catch (exception: IllegalAccessException) {
                exception.printStackTrace()
            }
        }
        return map
    }

    @JvmStatic
    private fun getAllProperties(): Map<String, KryptonProperty<*>> {
        if (cachedProperties != null) return cachedProperties!!
        val builtins = collectFieldProperties()
        cachedProperties = builtins
        return builtins
    }
}
