package net.aquamine.generators.data

object ReflectionHelper {

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    inline fun <T : Any, reified I : Any> getHiddenField(fieldName: String, instance: I?): T? {
        val field = I::class.java.getDeclaredField(fieldName)
        field.setAccessible(true)
        return field.get(instance) as T
    }

}