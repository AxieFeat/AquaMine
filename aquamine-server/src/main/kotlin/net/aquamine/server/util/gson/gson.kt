package net.aquamine.server.util.gson

import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import java.nio.file.Path
import java.util.stream.Stream
import kotlin.io.path.reader
import kotlin.io.path.writer

fun Path.jsonReader(): JsonReader = JsonReader(reader())

fun Path.jsonWriter(): JsonWriter = JsonWriter(writer())

inline fun <T, C : MutableCollection<T>> JsonReader.readListTo(result: C, block: JsonReader.() -> T?): C {
    beginArray()
    while (hasNext()) {
        block()?.let(result::add)
    }
    endArray()
    return result
}

inline fun <T> JsonReader.readPersistentList(block: JsonReader.() -> T?): PersistentList<T> =
    readListTo(persistentListOf<T>().builder(), block).build()

inline fun JsonWriter.array(block: JsonWriter.() -> Unit) {
    beginArray()
    block()
    endArray()
}

inline fun <T> JsonWriter.array(iterable: Iterable<T>, block: JsonWriter.(T) -> Unit) {
    array { iterable.forEach { block(it) } }
}

inline fun <T> JsonWriter.array(stream: Stream<T>, crossinline block: JsonWriter.(T) -> Unit) {
    array { stream.forEach { block(it) } }
}
