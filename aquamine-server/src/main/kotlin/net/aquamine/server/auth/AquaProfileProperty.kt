package net.aquamine.server.auth

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import net.aquamine.api.auth.ProfileProperty
import net.aquamine.server.util.gson.readPersistentList
import java.io.StringReader

@JvmRecord
data class AquaProfileProperty(override val name: String, override val value: String, override val signature: String?) : ProfileProperty {

    override fun withSignature(signature: String?): ProfileProperty = copy(signature = signature)

    override fun withoutSignature(): ProfileProperty = copy(signature = null)

    object Factory : ProfileProperty.Factory {

        override fun of(name: String, value: String, signature: String?): ProfileProperty = AquaProfileProperty(name, value, signature)
    }

    object Adapter : TypeAdapter<ProfileProperty>() {

        @JvmStatic
        fun readJsonList(json: String): PersistentList<ProfileProperty> = readList(JsonReader(StringReader(json)))

        @JvmStatic
        fun readList(reader: JsonReader): PersistentList<ProfileProperty> {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull()
                return persistentListOf()
            }
            return reader.readPersistentList(::read)
        }

        override fun read(reader: JsonReader): ProfileProperty? {
            reader.beginObject()

            var name: String? = null
            var value: String? = null
            var signature: String? = null
            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "name" -> name = reader.nextString()
                    "value" -> value = reader.nextString()
                    "signature" -> signature = reader.nextString()
                }
            }

            reader.endObject()
            if (name == null || value == null) return null // We can't complete a profile without these
            return AquaProfileProperty(name, value, signature)
        }

        override fun write(writer: JsonWriter, value: ProfileProperty) {
            writer.beginObject()
            writer.name("name")
            writer.value(value.name)
            writer.name("value")
            writer.value(value.value)
            if (value.signature != null) {
                writer.name("signature")
                writer.value(value.signature)
            }
            writer.endObject()
        }
    }
}
