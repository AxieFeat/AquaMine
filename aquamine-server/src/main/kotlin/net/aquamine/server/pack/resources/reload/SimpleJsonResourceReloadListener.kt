package net.aquamine.server.pack.resources.reload

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.stream.JsonReader
import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import net.aquamine.server.pack.resources.ResourceManager
import java.io.IOException

abstract class SimpleJsonResourceReloadListener(
    private val gson: Gson,
    private val directory: String
) : SimplePreparableReloadListener<Map<Key, JsonElement>>() {

    override fun prepare(manager: ResourceManager): Map<Key, JsonElement> {
        val result = HashMap<Key, JsonElement>()
        manager.listResources(directory) { it.value().endsWith(PATH_SUFFIX) }.forEach {
            val key = it.key
            val path = key.value()
            val location = Key.key(key.namespace(), path.substring(directory.length + 1, path.length - PATH_SUFFIX_LENGTH))
            try {
                it.value.openAsReader().use { reader ->
                    val json = gson.getAdapter(JsonElement::class.java).read(JsonReader(reader))
                    if (json != null) {
                        check(result.put(location, json) == null) { "Duplicate data file ignored with ID $location!" }
                    } else {
                        LOGGER.error("Failed to load data file $location from $key as it's null or empty!")
                    }
                }
            // Kotlin please add multicatch soon
            } catch (exception: IllegalArgumentException) {
                logReadError(location, key, exception)
            } catch (exception: IOException) {
                logReadError(location, key, exception)
            } catch (exception: JsonParseException) {
                logReadError(location, key, exception)
            }
        }
        return result
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
        private const val PATH_SUFFIX = ".json"
        private const val PATH_SUFFIX_LENGTH = PATH_SUFFIX.length

        @JvmStatic
        private fun logReadError(location: Key, key: Key, exception: Exception) {
            LOGGER.error("Failed to parse data file $location from $key!", exception)
        }
    }
}
