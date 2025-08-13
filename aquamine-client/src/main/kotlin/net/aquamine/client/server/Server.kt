package net.aquamine.client.server

import net.aquamine.server.AquaPlatform
import net.aquamine.server.auth.GameProfileCache
import net.aquamine.server.config.AquaConfig
import net.aquamine.server.server.Bootstrap
import net.aquamine.server.server.InitContext
import net.aquamine.server.server.StatisticsSerializer
import net.aquamine.server.ticking.TickSchedulerThread
import net.aquamine.server.util.executor.DefaultUncaughtExceptionHandler
import net.aquamine.server.world.chunk.VanillaChunkLoader
import net.aquamine.server.world.data.DefaultPlayerDataSerializer
import net.aquamine.server.world.data.DefaultWorldDataSerializer
import org.apache.logging.log4j.LogManager
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.CompletableFuture

class IntegratedServerBootstrap : Thread("Integrated Server") {

    private val server = CompletableFuture<IntegratedServer>()

    fun startServer(): CompletableFuture<IntegratedServer> {
        start()
        return server
    }

    override fun run() {
        val logger = LogManager.getLogger("AquaMine")
        logger.info("Starting AquaMine server version ${AquaPlatform.version} for Minecraft ${AquaPlatform.minecraftVersion}...")

        // Run the bootstrap
        Bootstrap.preload()
        Bootstrap.validate()

        val config = AquaConfig.load(configFile)

        val cache = GameProfileCache(userCacheFile)
        cache.loadAll()

        val defaultWorldFolder = worldFolder.resolve(config.world.name)

        val playerDataFolder = defaultWorldFolder.resolve("playerdata")
        if (config.advanced.serializePlayerData && !Files.exists(playerDataFolder)) {
            try {
                Files.createDirectories(playerDataFolder)
            } catch (exception: Exception) {
                logger.error("Unable to create player data directory!", exception)
                return
            }
        }

        val worldDataSerializer = DefaultWorldDataSerializer(worldFolder)
        val playerDataSerializer = DefaultPlayerDataSerializer(playerDataFolder)
        val statsSerializer = StatisticsSerializer(defaultWorldFolder.resolve("stats"))
        val chunkLoader = VanillaChunkLoader(defaultWorldFolder)

        val initContext = InitContext(statsSerializer, worldDataSerializer, playerDataSerializer, chunkLoader)
        val server = IntegratedServer(config, cache, initContext)
        this.server.complete(server)
        if (!server.initialize()) {
            // We just return here if initialisation fails. The error will already have been logged.
            return
        }

        TickSchedulerThread(server).start()

        val shutdownThread = Thread({ server.stop() }, "Integrated Server Shutdown Thread")
        shutdownThread.uncaughtExceptionHandler = DefaultUncaughtExceptionHandler(logger)
        Runtime.getRuntime().addShutdownHook(shutdownThread)
    }

    companion object {
        private val configFile = Path.of("config.conf")
        private val userCacheFile = Path.of("usercache.json")
        private val worldFolder = Path.of("")
    }
}
