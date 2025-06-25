package net.aquamine.server.config.serializer

import org.spongepowered.configurate.serialize.ScalarSerializer
import org.spongepowered.configurate.serialize.SerializationException
import java.lang.reflect.Type
import java.util.Locale
import java.util.function.Predicate

object LocaleTypeSerializer : ScalarSerializer<Locale>(Locale::class.java) {

    override fun serialize(item: Locale, typeSupported: Predicate<Class<*>>): Any = item.toString()

    override fun deserialize(type: Type, source: Any): Locale {
        if (source !is String) throw SerializationException("Locale must be a string!")
        return Locale(source)
    }
}
