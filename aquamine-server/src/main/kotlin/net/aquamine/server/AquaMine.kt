package net.aquamine.server

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.path
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

fun main(args: Array<String>) {
    AquaCLI().main(args)
}

private class AquaCLI : CliktCommand(
    /**
     * All the command-line options for configuring AquaMine. Useful for use in scripts.
     *         Note: All options provided here will OVERRIDE those provided in the config!
     */
    name = "AquaMine"
) {

    // Flags
    private val version by option("-v", "--version")
        .flag()
        .help("Prints the version and exits")
    private val configOnly by option("--init", "--config-only", "--init-settings")
        .flag()
        .help("Creates the config file and exits")

    // Folders
    private val configFile by option("--config", "--config-file")
        .help("Configuration file path for the server")
        .path(canBeDir = false, canBeSymlink = false, mustBeReadable = true, mustBeWritable = true)
        .default(Path.of("config.conf"))
    private val userCacheFile by option("--user-cache-file")
        .help("File where users' profiles are stored.")
        .path(canBeDir = false, canBeSymlink = false, mustBeReadable = true, mustBeWritable = true)
        .default(Path.of("usercache.json"))
    private val worldFolder by option("--world-folder", "--world-directory", "--world-dir", "--universe")
        .help("Folder where worlds are stored")
        .path(canBeFile = false, canBeSymlink = false, mustBeReadable = true, mustBeWritable = true)
        .default(Path.of(""))

    override fun run() {
        if (version) {
            println("AquaMine version ${AquaPlatform.version} for Minecraft ${AquaPlatform.minecraftVersion}")
            return
        }

        val logger = LogManager.getLogger("AquaMine")
        logger.info("Starting AquaMine server version ${AquaPlatform.version} for Minecraft ${AquaPlatform.minecraftVersion}...")

        // Run the bootstrap
        Bootstrap.preload()
//        Bootstrap.validate()

        val config = AquaConfig.load(configFile)
        if (configOnly) {
            logger.info("Successfully initialized config file located at $configFile.")
            return
        }

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
        val server = AquaServer(config, cache, initContext)
        if (!server.initialize()) {
            // We just return here if initialisation fails. The error will already have been logged.
            return
        }

        TickSchedulerThread(server).start()

        val shutdownThread = Thread({ server.stop() }, "Server Shutdown Thread")
        shutdownThread.uncaughtExceptionHandler = DefaultUncaughtExceptionHandler(logger)
        Runtime.getRuntime().addShutdownHook(shutdownThread)
    }
}

