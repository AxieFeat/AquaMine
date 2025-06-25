package net.aquamine.server.pack

import org.apache.logging.log4j.LogManager
import net.aquamine.server.pack.metadata.MetadataSectionSerializer
import net.aquamine.server.util.gson.GsonHelper
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

abstract class AbstractPackResources protected constructor(private val name: String, private val builtin: Boolean) : PackResources {

    final override fun packId(): String = name

    final override fun isBuiltin(): Boolean = builtin

    final override fun <T> getMetadataSection(serializer: MetadataSectionSerializer<T>): T? {
        val resource = getRootResource(PackResources.PACK_META) ?: return null
        return resource.get().use { getMetadataFromStream(serializer, it) }
    }

    companion object {

        private val LOGGER = LogManager.getLogger()

        @JvmStatic
        fun <T> getMetadataFromStream(serializer: MetadataSectionSerializer<T>, input: InputStream): T? {
            val json = try {
                BufferedReader(InputStreamReader(input, Charsets.UTF_8)).use(GsonHelper::parse)
            } catch (exception: Exception) {
                LOGGER.error("Failed to load ${serializer.metadataSectionName()} metadata!", exception)
                return null
            }
            if (!json.has(serializer.metadataSectionName())) return null
            return try {
                serializer.fromJson(json.getAsJsonObject(serializer.metadataSectionName()))
            } catch (exception: Exception) {
                LOGGER.error("Failed to load ${serializer.metadataSectionName()} metadata!", exception)
                null
            }
        }
    }
}
