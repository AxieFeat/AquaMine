package net.aquamine.server.pack

import com.google.common.base.Splitter
import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import net.aquamine.server.util.ImmutableSets
import net.aquamine.server.util.Keys
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.function.Supplier
import java.util.zip.ZipFile

class FilePackResources(name: String, private val file: File, builtin: Boolean) : AbstractPackResources(name, builtin) {

    private var zipFile: ZipFile? = null
    private var failedToLoad = false

    private fun getOrCreateZipFile(): ZipFile? {
        if (failedToLoad) return null
        if (zipFile == null) {
            try {
                zipFile = ZipFile(file)
            } catch (exception: IOException) {
                LOGGER.error("Failed to open pack $file!", exception)
                failedToLoad = true
                return null
            }
        }
        return zipFile!!
    }

    override fun getRootResource(vararg path: String): Supplier<InputStream>? = getResource(path.joinToString("/"))

    override fun getResource(packType: PackType, location: Key): Supplier<InputStream>? = getResource(getPathFromLocation(packType, location))

    private fun getResource(path: String): Supplier<InputStream>? {
        val zip = getOrCreateZipFile() ?: return null
        return zip.getEntry(path)?.let { Supplier { zip.getInputStream(it) } }
    }

    override fun namespaces(packType: PackType): Set<String> {
        val zip = getOrCreateZipFile() ?: return ImmutableSets.of()
        val entries = zip.entries()
        val namespaces = HashSet<String>()

        while (entries.hasMoreElements()) {
            val entry = entries.nextElement()
            val name = entry.name
            if (!name.startsWith("${packType.directory}/")) continue
            val parts = SPLITTER.splitToList(name)
            if (parts.size <= 1) continue
            val namespace = parts.get(1)
            if (namespace == namespace.lowercase()) {
                namespaces.add(namespace)
            } else {
                LOGGER.warn("Ignored non-lowercase namespace $namespace in $file.")
            }
        }
        return namespaces
    }

    @Suppress("ProtectedMemberInFinalClass")
    protected fun finalize() {
        close()
    }

    override fun close() {
        val zip = zipFile ?: return
        try {
            zip.close()
        } catch (_: IOException) {
            // Do nothing - we're closing anyway
        }
        zipFile = null
    }

    override fun listResources(packType: PackType, namespace: String, path: String, output: PackResources.ResourceOutput) {
        val zip = getOrCreateZipFile() ?: return
        val entries = zip.entries()
        val namespaceFolder = "${packType.directory}/$namespace/"
        val pathFolder = "$namespaceFolder$path/"

        while (entries.hasMoreElements()) {
            val entry = entries.nextElement()
            if (entry.isDirectory) continue
            val name = entry.name
            if (!name.startsWith(pathFolder)) continue
            val relativePath = name.substring(namespaceFolder.length)
            val key = Keys.create(namespace, relativePath)
            if (key != null) {
                output.accept(key) { zip.getInputStream(entry) }
            } else {
                LOGGER.warn("Invalid path in data pack: $namespace:$relativePath! Ignoring...")
            }
        }
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
        private val SPLITTER = Splitter.on("/").omitEmptyStrings().limit(3)

        @JvmStatic
        private fun getPathFromLocation(packType: PackType, location: Key): String =
            "${packType.directory}/${location.namespace()}/${location.value()}"
    }
}
