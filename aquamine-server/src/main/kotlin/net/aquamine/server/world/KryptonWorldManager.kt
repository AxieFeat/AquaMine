package net.aquamine.server.world

import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.api.world.World
import net.aquamine.api.world.WorldManager
import net.aquamine.server.KryptonServer
import net.aquamine.server.world.util.ChunkProgressListener
import net.aquamine.server.coordinate.SectionPos
import net.aquamine.server.registry.KryptonDynamicRegistries
import net.aquamine.server.util.executor.DefaultPoolUncaughtExceptionHandler
import net.aquamine.server.util.executor.ThreadPoolBuilder
import net.aquamine.server.util.executor.daemonThreadFactory
import net.aquamine.server.world.chunk.ChunkLoader
import net.aquamine.server.world.data.WorldDataSerializer
import net.aquamine.server.world.dimension.KryptonDimensionType
import net.aquamine.server.world.dimension.KryptonDimensionTypes
import java.io.File
import java.time.Duration
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.max

class KryptonWorldManager(
    override val server: KryptonServer,
    private val dataSerializer: WorldDataSerializer,
    private val chunkLoader: ChunkLoader
) : WorldManager {

    private val worldExecutor = ThreadPoolBuilder.create()
        .coreSize(0)
        .maximumSize(max(1, Runtime.getRuntime().availableProcessors() / 2))
        .keepAlive(Duration.ofSeconds(60))
        .factory(daemonThreadFactory("World Handler #%d") { setUncaughtExceptionHandler(DefaultPoolUncaughtExceptionHandler(LOGGER)) })
        .build()
    private val data = checkNotNull(dataSerializer.load(name())) { "You must provide an existing world for Krypton!" }

    override val worlds: MutableMap<ResourceKey<World>, KryptonWorld> = ConcurrentHashMap()
    override val default: KryptonWorld
        get() = worlds[World.OVERWORLD] ?: error("The default world has not yet been loaded!")

    private fun name(): String = server.config.world.name

    fun init() {
        create()
        prepare()
    }

    override fun getWorld(key: Key): World? = worlds[ResourceKey.of(ResourceKeys.DIMENSION, key)]

    override fun loadWorld(key: Key): CompletableFuture<KryptonWorld?> {
        val resourceKey = ResourceKey.of(ResourceKeys.DIMENSION, key)
        if (resourceKey === World.OVERWORLD) return failFuture(IllegalArgumentException("The default world cannot be loaded!"))
        val loaded = worlds[resourceKey]
        if (loaded != null) return CompletableFuture.completedFuture(loaded)

        val dimensionType = KryptonDynamicRegistries.DIMENSION_TYPE.get(key) as? KryptonDimensionType
            ?: return failFuture(IllegalStateException("No dimension type found for given key $key!"))

        LOGGER.info("Loading world ${key.asString()}...")
        val folderName = getStorageFolder(key)
        val isSubWorld = folderName == "DIM-1" || folderName == "DIM1"

        val path = if (isSubWorld) folderName else key.namespace() + File.separator + key.value()
        return CompletableFuture.supplyAsync({
            val worldData = dataSerializer.load(path) ?: return@supplyAsync null
            KryptonWorld(server, worldData, resourceKey, dimensionType, chunkLoader)
        }, worldExecutor)
    }

    override fun saveWorld(world: World): CompletableFuture<Void> {
        val kryptonWorld = world.downcast() // Moved outside of the block to fail fast
        return CompletableFuture.runAsync({ kryptonWorld.save() }, worldExecutor)
    }

    override fun isLoaded(key: Key): Boolean = worlds.containsKey(ResourceKey.of(ResourceKeys.DIMENSION, key))

    fun saveAllChunks(suppressLog: Boolean, forced: Boolean): Boolean {
        var successful = false
        worlds.values.forEach { world ->
            if (!suppressLog) LOGGER.info("Saving chunks for world $world in ${world.dimension.location}...")
            if (!world.doNotSave || forced) world.save()
            successful = true
        }
        dataSerializer.save(name(), data)
        return successful
    }

    private fun create() {
        val dimension = KryptonDimensionTypes.OVERWORLD
        val world = KryptonWorld(server, data, World.OVERWORLD, dimension, chunkLoader)
        worlds.put(World.OVERWORLD, world)
        if (!data.isInitialized) data.isInitialized = true
    }

    private fun prepare() {
        val listener = ChunkProgressListener(9)
        LOGGER.info("Preparing start region for dimension ${default.dimension.location}...")
        listener.tick()
        default.chunkManager.loadStartingArea(SectionPos.blockToSection(default.data.spawnX), SectionPos.blockToSection(default.data.spawnZ)) {
            listener.updateStatus()
        }
        listener.stop()
    }

    companion object {

        private val LOGGER = LogManager.getLogger()

        @JvmStatic
        private fun <T> failFuture(exception: Exception): CompletableFuture<T> = CompletableFuture.failedFuture(exception)

        @JvmStatic
        private fun getStorageFolder(location: Key): String = when (location) {
            World.OVERWORLD.location -> ""
            World.NETHER.location -> "DIM-1"
            World.END.location -> "DIM1"
            else -> location.value()
        }
    }
}
