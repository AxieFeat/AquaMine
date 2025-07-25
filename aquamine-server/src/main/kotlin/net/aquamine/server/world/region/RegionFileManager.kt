package net.aquamine.server.world.region

import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap
import net.aquamine.server.coordinate.ChunkPos
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.io.TagCompression
import xyz.axie.nbt.io.TagIO
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * Manages region files. That's literally it.
 */
class RegionFileManager(private val folder: Path) : AutoCloseable {

    private val regionCache = Long2ObjectLinkedOpenHashMap<RegionFile>()
    private val regionCacheLock = ReentrantLock()

    fun read(x: Int, z: Int): CompoundTag? = getRegionFile(x, z).getChunkDataInputStream(x, z)?.use { TagIO.read(it, TagCompression.NONE) }

    fun write(x: Int, z: Int, data: CompoundTag?) {
        val file = getRegionFile(x, z)
        if (data == null) {
            file.clear(x, z)
            return
        }
        file.getChunkDataOutputStream(x, z).use { TagIO.write(it, data, TagCompression.NONE) }
    }

    private fun getRegionFile(x: Int, z: Int): RegionFile {
        val regionX = x shr 5
        val regionZ = z shr 5
        val serialized = ChunkPos.pack(regionX, regionZ)
        val cachedFile = regionCacheLock.withLock { regionCache.getAndMoveToFirst(serialized) }
        if (cachedFile != null) return cachedFile

        if (regionCache.size >= MAX_CACHE_SIZE) regionCacheLock.withLock { regionCache.removeLast().close() }
        Files.createDirectories(folder)
        val path = folder.resolve("r.$regionX.$regionZ$ANVIL_EXTENSION")
        val regionFile = RegionFile(path, folder)
        regionCacheLock.withLock { regionCache.putAndMoveToFirst(serialized, regionFile) }
        return regionFile
    }

    fun flush() {
        regionCacheLock.withLock { regionCache.values.forEach(RegionFile::flush) }
    }

    override fun close() {
        regionCacheLock.withLock { regionCache.values.forEach(RegionFile::close) }
    }

    companion object {

        private const val ANVIL_EXTENSION = ".mca"
        private const val MAX_CACHE_SIZE = 256
    }
}
