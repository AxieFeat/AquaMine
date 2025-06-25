package net.aquamine.server.util.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.io.Reader
import java.io.StringReader
import java.io.StringWriter

object GsonHelper {

    @JvmField
    val GSON: Gson = GsonBuilder().create()

    @JvmStatic
    fun parse(json: String): JsonObject = parse(StringReader(json), false)

    @JvmStatic
    fun parse(reader: Reader): JsonObject = parse(reader, false)

    @JvmStatic
    fun parse(reader: Reader, lenient: Boolean): JsonObject {
        try {
            return GSON.getAdapter(JsonObject::class.java).read(JsonReader(reader).apply { isLenient = lenient })
        } catch (exception: IOException) {
            throw JsonParseException(exception)
        }
    }

    @JvmStatic
    fun toStableString(value: JsonElement): String {
        val output = StringWriter()
        val writer = JsonWriter(output)
        try {
            writeValueOrdered(writer, value, Comparator.naturalOrder())
        } catch (exception: IOException) {
            throw RuntimeException(exception)
        }
        return output.toString()
    }

    @JvmStatic
    private fun writeValueOrdered(writer: JsonWriter, element: JsonElement?, comparator: Comparator<String>?) {
        if (element == null || element.isJsonNull) {
            writer.nullValue()
            return
        }
        if (element is JsonPrimitive) {
            when {
                element.isNumber -> writer.value(element.asNumber)
                element.isBoolean -> writer.value(element.asBoolean)
                else -> writer.value(element.asString)
            }
            return
        }
        if (element.isJsonArray) {
            writer.beginArray()
            element.asJsonArray.forEach { writeValueOrdered(writer, it, comparator) }
            writer.endArray()
            return
        }
        require(element.isJsonObject) { "Cannot write JsonElement of type ${element.javaClass}!" }
        writer.beginObject()
        sortByKeyIfNeeded(element.asJsonObject.entrySet(), comparator).forEach {
            writer.name(it.key)
            writeValueOrdered(writer, it.value, comparator)
        }
        writer.endObject()
    }

    @JvmStatic
    private fun sortByKeyIfNeeded(
        entries: Collection<Map.Entry<String, JsonElement>>,
        comparator: Comparator<String>?
    ): Collection<Map.Entry<String, JsonElement>> {
        if (comparator == null) return entries
        return entries.sortedWith(java.util.Map.Entry.comparingByKey(comparator))
    }
}
