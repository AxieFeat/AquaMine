package net.aquamine.server.pack

import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import net.aquamine.server.pack.metadata.MetadataSectionSerializer
import net.aquamine.server.util.FileUtil
import net.aquamine.server.util.ImmutableLists
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.function.Consumer
import java.util.function.Supplier

class VanillaPackResources(
    private val metadata: BuiltInMetadata,
    private val namespaces: Set<String>,
    private val rootPaths: List<Path>,
    private val pathsForType: Map<PackType, List<Path>>
) : PackResources {

    override fun getRootResource(vararg path: String): Supplier<InputStream>? {
        FileUtil.validatePath(path)
        val segments = ImmutableLists.ofArray(path)
        rootPaths.forEach { rootPath ->
            val relative = FileUtil.resolvePath(rootPath, segments)
            if (Files.exists(relative)) return Supplier { Files.newInputStream(relative) }
        }
        return null
    }

    fun listRawPaths(packType: PackType, location: Key, action: Consumer<Path>) {
        FileUtil.decomposePath(location.value()).get()
            .ifLeft { segments ->
                pathsForType[packType]!!.forEach { action.accept(FileUtil.resolvePath(it.resolve(location.namespace()), segments)) }
            }
            .ifRight { LOGGER.error("Invalid path $location! ${it.message}") }
    }

    override fun listResources(packType: PackType, namespace: String, path: String, output: PackResources.ResourceOutput) {
        FileUtil.decomposePath(path).get()
            .ifLeft { segments ->
                val paths = pathsForType[packType]!!
                val pathCount = paths.size
                if (pathCount == 1) {
                    getResources(output, namespace, paths[0], segments)
                    return@ifLeft
                }
                val streams = HashMap<Key, Supplier<InputStream>>()
                for (i in 0 until pathCount - 1) {
                    getResources(streams::putIfAbsent, namespace, paths[i], segments)
                }
                val lastPath = paths[pathCount - 1]
                if (streams.isEmpty()) {
                    getResources(output, namespace, lastPath, segments)
                } else {
                    getResources(streams::putIfAbsent, namespace, lastPath, segments)
                    streams.forEach(output)
                }
            }
            .ifRight { LOGGER.error("Invalid path $path! ${it.message}") }
    }

    override fun getResource(packType: PackType, location: Key): Supplier<InputStream> {
        return FileUtil.decomposePath(location.value()).get().map(
            { segments ->
                pathsForType[packType]!!.forEach { path ->
                    val relative = FileUtil.resolvePath(path.resolve(location.namespace()), segments)
                    if (Files.exists(relative)) return@map Supplier { Files.newInputStream(relative) }
                }
                null
            },
            {
                LOGGER.error("Invalid path $location! ${it.message}")
                null
            }
        )
    }

    override fun namespaces(packType: PackType): Set<String> = namespaces

    @Suppress("UNCHECKED_CAST")
    override fun <T> getMetadataSection(serializer: MetadataSectionSerializer<T>): T? {
        val resource = getRootResource(PackResources.PACK_META)
        if (resource != null) {
            try {
                val result = resource.get().use { stream -> AbstractPackResources.getMetadataFromStream(serializer, stream) }
                return result ?: metadata.get(serializer)
            } catch (_: IOException) {
            }
        }
        return metadata.get(serializer)
    }

    override fun packId(): String = "vanilla"

    override fun isBuiltin(): Boolean = true

    override fun close() {
        // Nothing to close
    }

    companion object {

        private val LOGGER = LogManager.getLogger()

        @JvmStatic
        private fun getResources(output: PackResources.ResourceOutput, namespace: String, path: Path, segments: List<String>) {
            PathPackResources.listPath(namespace, path.resolve(namespace), segments, output)
        }
    }
}
