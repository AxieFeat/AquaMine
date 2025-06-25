package net.aquamine.server.pack

import net.kyori.adventure.key.Key
import net.aquamine.server.pack.metadata.MetadataSectionSerializer
import java.io.InputStream
import java.util.function.BiConsumer
import java.util.function.Supplier

interface PackResources : AutoCloseable {

    fun packId(): String

    fun namespaces(packType: PackType): Set<String>

    fun isBuiltin(): Boolean = false

    fun getRootResource(vararg path: String): Supplier<InputStream>?

    fun getResource(packType: PackType, location: Key): Supplier<InputStream>?

    fun listResources(packType: PackType, namespace: String, path: String, output: ResourceOutput)

    fun <T> getMetadataSection(serializer: MetadataSectionSerializer<T>): T?

    fun interface ResourceOutput : BiConsumer<Key, Supplier<InputStream>>

    companion object {

        const val METADATA_EXTENSION: String = ".mcmeta"
        const val PACK_META: String = "pack$METADATA_EXTENSION"
    }
}
