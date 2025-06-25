package net.aquamine.server.util

import org.apache.logging.log4j.LogManager
import java.lang.reflect.Field

object Reflection {

    private val LOGGER = LogManager.getLogger()

    @JvmStatic
    inline fun <reified T, R> accessField(name: String, instance: Any? = null): R = accessField(T::class.java, name, instance)

    @JvmStatic
    fun <R> accessField(clazz: Class<*>, name: String, instance: Any? = null): R {
        return try {
            val value = getField(clazz, name).get(instance)
            @Suppress("UNCHECKED_CAST") // If this fails, it's on the client.
            value as R
        } catch (exception: Exception) {
            LOGGER.error("Error whilst trying to get the value of $name in ${clazz.canonicalName}!", exception)
            throw exception
        }
    }

    @JvmStatic
    fun modifyField(clazz: Class<*>, name: String, instance: Any?, value: Any) {
        try {
            getField(clazz, name).set(instance, value)
        } catch (exception: Exception) {
            LOGGER.error("Error whilst trying to set the value of $name in ${clazz.canonicalName}!", exception)
            throw exception
        }
    }

    @JvmStatic
    fun modifyStaticField(clazz: Class<*>, name: String, value: Any) {
        modifyField(clazz, name, null, value)
    }

    /**
     * Gets the field in the type with the name provided.
     *
     * Will propagate any exceptions from getDeclaredField or setAccessible.
     */
    @JvmStatic
    private fun getField(clazz: Class<*>, name: String): Field {
        try {
            return clazz.getDeclaredField(name).apply { isAccessible = true }
        } catch (exception: Exception) {
            LOGGER.error("Error whilst trying to get field $name in ${clazz.canonicalName}!", exception)
            throw exception
        }
    }
}
