package net.aquamine.server.pack.resources

import net.aquamine.server.pack.metadata.MetadataSectionSerializer
import net.aquamine.server.util.gson.GsonHelper
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.function.Supplier

interface ResourceMetadata {

    fun <T> getSection(serializer: MetadataSectionSerializer<T>): T?

    companion object {

        @JvmField
        val EMPTY: ResourceMetadata = object : ResourceMetadata {
            override fun <T> getSection(serializer: MetadataSectionSerializer<T>): T? = null
        }
        @JvmField
        val EMPTY_SUPPLIER: Supplier<ResourceMetadata> = Supplier { EMPTY }

        @JvmStatic
        fun fromJsonStream(input: InputStream): ResourceMetadata = BufferedReader(InputStreamReader(input, Charsets.UTF_8)).use {
            val json = GsonHelper.parse(it)
            object : ResourceMetadata {
                override fun <T> getSection(serializer: MetadataSectionSerializer<T>): T? {
                    val name = serializer.metadataSectionName()
                    return if (json.has(name)) serializer.fromJson(json.getAsJsonObject(name)) else null
                }
            }
        }
    }
}
