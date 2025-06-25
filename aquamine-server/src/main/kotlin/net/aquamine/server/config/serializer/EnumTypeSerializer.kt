package net.aquamine.server.config.serializer

import org.spongepowered.configurate.serialize.ScalarSerializer
import org.spongepowered.configurate.serialize.SerializationException
import java.lang.reflect.Type
import java.util.function.Function
import java.util.function.IntFunction
import java.util.function.Predicate

class EnumTypeSerializer<E : Enum<E>>(
    type: Class<E>,
    private val typeName: String,
    private val fromId: IntFunction<E?>,
    private val fromName: Function<String, E?>
) : ScalarSerializer<E>(type) {

    override fun serialize(item: E, typeSupported: Predicate<Class<*>>?): Any = item.name.lowercase()

    override fun deserialize(type: Type, source: Any): E = when (source) {
        is Int -> fromId.apply(source) ?: throw SerializationException("$source is not a valid $typeName ID!")
        is String -> fromName.apply(source.lowercase()) ?: throw SerializationException("$source is not a valid $typeName name!")
        else -> throw SerializationException("Expected either an integer or a string for this $typeName, got ${source.javaClass.simpleName}!")
    }

    companion object {

        @JvmStatic
        inline fun <reified E : Enum<E>> of(
            typeName: String,
            crossinline fromId: (Int) -> E?,
            crossinline fromName: (String) -> E?
        ): EnumTypeSerializer<E> {
            return EnumTypeSerializer(E::class.java, typeName, { fromId(it) }, { fromName(it) })
        }
    }
}
