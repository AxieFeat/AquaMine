package net.aquamine.server.util.uuid

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.util.UUID

object MojangUUIDTypeAdapter : TypeAdapter<UUID>() {

    private val REPLACEMENT_REGEX = "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})".toRegex()

    @JvmStatic
    fun fromString(string: String): UUID = UUID.fromString(string.replace(REPLACEMENT_REGEX, "$1-$2-$3-$4-$5"))

    @JvmStatic
    fun toString(uuid: UUID): String = uuid.toString().replace("-", "")

    override fun read(reader: JsonReader): UUID = fromString(reader.nextString())

    override fun write(out: JsonWriter, value: UUID?) {
        out.value(value?.let(MojangUUIDTypeAdapter::toString))
    }
}
