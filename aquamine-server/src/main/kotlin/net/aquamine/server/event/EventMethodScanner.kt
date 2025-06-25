package net.aquamine.server.event

import net.aquamine.api.event.Event
import net.aquamine.api.event.Listener
import java.lang.reflect.Method
import java.lang.reflect.Modifier

object EventMethodScanner {

    @JvmStatic
    fun <T : Event> collectMethods(targetClass: Class<*>, eventType: Class<T>): Map<String, EventListenerMethod<T>> {
        val collected = HashMap<String, EventListenerMethod<T>>()
        collectMethods(targetClass, eventType, collected)
        return collected
    }

    @JvmStatic
    private fun <T : Event> collectMethods(targetClass: Class<*>, eventType: Class<T>, collected: MutableMap<String, EventListenerMethod<T>>) {
        for (method in targetClass.declaredMethods) {
            if (!method.isAnnotationPresent(Listener::class.java)) continue

            val key = "${method.name}(${method.parameterTypes.joinToString(",") { it.name }})"
            if (collected.containsKey(key)) continue

            if (Modifier.isStatic(method.modifiers)) error(method, targetClass, "must not be static")
            if (Modifier.isAbstract(method.modifiers)) error(method, targetClass, "must not be abstract")

            val parameterCount = method.parameterCount
            if (parameterCount != 1) error(method, targetClass, "must have exactly one parameter")

            val parameterEventType = method.parameterTypes[0]
            if (!eventType.isAssignableFrom(parameterEventType)) error(method, targetClass, "must have an event parameter")

            @Suppress("UNCHECKED_CAST")
            parameterEventType as Class<T>
            collected.put(key, EventListenerMethod(method, parameterEventType))
        }

        val superclass = targetClass.superclass
        if (superclass != Any::class.java) collectMethods(superclass, eventType, collected)
    }

    @JvmStatic
    private fun error(method: Method, targetClass: Class<*>, message: String) {
        throw IllegalArgumentException("Invalid listener method ${method.name} in ${targetClass.name}: $message!")
    }

    @JvmRecord
    data class EventListenerMethod<T : Event>(val method: Method, val eventType: Class<T>)
}
