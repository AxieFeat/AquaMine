package net.aquamine.server

import net.aquamine.api.event.GlobalEventNode
import net.aquamine.api.scheduling.TaskTime
import net.aquamine.api.world.World
import net.aquamine.server.auth.GameProfileCache
import net.aquamine.server.command.AquaCommandManager
import net.aquamine.server.commands.AquaPermission
import net.aquamine.server.config.AquaConfig
import net.aquamine.server.config.category.ProxyCategory
import net.aquamine.server.console.AquaConsole
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.event.AquaGlobalEventNode
import net.aquamine.server.event.server.AquaServerStartEvent
import net.aquamine.server.event.server.AquaServerStopEvent
import net.aquamine.server.network.PacketFraming
import net.aquamine.server.network.socket.NetworkServer
import net.aquamine.server.packet.PacketRegistry
import net.aquamine.server.plugin.AquaPluginManager
import net.aquamine.server.plugin.loader.PluginLoader
import net.aquamine.server.scheduling.AquaScheduler
import net.aquamine.server.server.InitContext
import net.aquamine.server.server.PlayerManager
import net.aquamine.server.server.StatusManager
import net.aquamine.server.service.AquaServicesManager
import net.aquamine.server.ticking.TickDispatcher
import net.aquamine.server.ticking.TickThreadProvider
import net.aquamine.server.user.AquaUserManager
import net.aquamine.server.util.crypto.YggdrasilSessionKey
import net.aquamine.server.util.random.RandomSource
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.AquaWorldManager
import net.aquamine.server.world.chunk.AquaChunk
import org.apache.commons.lang3.SystemUtils
import org.apache.logging.log4j.LogManager
import java.io.IOException
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.UnixDomainSocketAddress
import java.util.*
import kotlin.math.abs
import kotlin.math.max

class AquaServer(override val config: AquaConfig, val profileCache: GameProfileCache, initContext: InitContext) : BaseServer {

    private val networkServer = NetworkServer(this)
    override val playerManager: PlayerManager = PlayerManager(this, initContext.playerDataSerializer, initContext.statisticsSerializer)
    val statusManager: StatusManager = StatusManager(playerManager, config.status.motd, config.status.maxPlayers)

    override val console: AquaConsole = AquaConsole(this)
    override val worldManager: AquaWorldManager = AquaWorldManager(this, initContext.worldDataSerializer, initContext.chunkLoader)
    override val commandManager: AquaCommandManager = AquaCommandManager()
    override val pluginManager: AquaPluginManager = AquaPluginManager()
    override val eventNode: GlobalEventNode = AquaGlobalEventNode
    override val servicesManager: AquaServicesManager = AquaServicesManager(this)
    override val scheduler: AquaScheduler = AquaScheduler()
    override val userManager: AquaUserManager = AquaUserManager(this, initContext.playerDataSerializer)
    private val random = RandomSource.create()

    private val tickDispatcher = TickDispatcher<AquaChunk>(TickThreadProvider.counter(), 4)
    @Volatile
    private var running = true

    init {
        PacketFraming.setCompressionThreshold(config.server.compressionThreshold)
        YggdrasilSessionKey.get()
    }

    fun tickDispatcher(): TickDispatcher<AquaChunk> = tickDispatcher

    override fun isRunning(): Boolean = running

    // The order of loading here is pretty important, as some things depend on
    // others to function properly.
    fun initialize(): Boolean {
        LOGGER.info("Starting AquaMine server on ${config.server.ip}:${config.server.port}...")
        val startTime = System.nanoTime()

        LOGGER.debug("Loading packets...")
        PacketRegistry.bootstrap()
        LOGGER.debug("Registering commands...")
        commandManager.registerBuiltins()

        LOGGER.info("Preparing world ${config.world.name}...")
        worldManager.init()

        // Load the console after the world. This ensures we don't get errors from trying to use the default world before it's ready.
        // TODO: See if there is something we can do better than this, like trying to accept the world when it's null.
        LOGGER.debug("Starting console handler...")
        console.run()

        // Load plugins here because most of everything they need is available now.
        loadPlugins()

        // Fire the event that signals the server starting. We fire it here so that plugins can listen to it as part of their lifecycle,
        // and we call it sync so plugins can finish initialising before we do.
        LOGGER.debug("Notifying plugins that the server is ready")
        eventNode.fire(AquaServerStartEvent)

        // Initialize console permissions after plugins are loaded. We do this here so plugins have had a chance to register their listeners
        // so they can actually catch this event and set up their own permission providers for the console.
        LOGGER.debug("Setting up console permissions...")
        console.setupPermissions()

        // Determine the correct bind address (UNIX domain socket or standard internet socket).
        val bindAddress = if (config.server.ip.startsWith("unix:")) {
            if (!SystemUtils.IS_OS_UNIX) {
                LOGGER.error("UNIX domain sockets are only supported on UNIX-like systems!")
                return false
            }
            if (config.proxy.mode == ProxyCategory.Mode.NONE) {
                LOGGER.error("UNIX domain sockets require IPs to be forwarded from a proxy!")
                return false
            }
            LOGGER.info("Using UNIX domain socket ${config.server.ip}")
            UnixDomainSocketAddress.of(config.server.ip)
        } else {
            InetSocketAddress(InetAddress.getByName(config.server.ip), config.server.port)
        }

        // Try binding to the port and start accepting connections
        LOGGER.debug("Starting Network Server...")
        try {
            networkServer.initialize(bindAddress)
        } catch (exception: IOException) {
            LOGGER.error("FAILED TO BIND TO PORT ${config.server.port}!", exception)
            return false
        }
        networkServer.start()

        setupAutosaveTasks()

        val doneTime = String.format(Locale.ROOT, "%.3fs", (System.nanoTime() - startTime) / 1.0E9)
        LOGGER.info("Done ($doneTime)! Type \"help\" for help.")
        return true
    }

    private fun setupAutosaveTasks() {
        if (config.world.autosaveInterval > 0) {
            val task = Runnable {
                LOGGER.info("Auto save started.")
                saveEverything(true, false)
                LOGGER.info("Auto save finished.")
            }
            scheduler.buildTask(task)
                .delay(TaskTime.ticks(config.world.autosaveInterval))
                .period(TaskTime.ticks(config.world.autosaveInterval))
                .sync()
                .schedule()
        }
        scheduler.buildTask { profileCache.saveIfNeeded() }
            .delay(TaskTime.ticks(SAVE_PROFILE_CACHE_INTERVAL))
            .period(TaskTime.ticks(SAVE_PROFILE_CACHE_INTERVAL))
            .sync()
            .schedule()
    }

    private fun loadPlugins() {
        LOGGER.info("Loading modules and plugins...")
        try {
            PluginLoader.createDefault(this).loadPlugins(this)
        } catch (exception: Exception) {
            LOGGER.error("Failed to load plugins and modules!", exception)
        }

        // Register all of the plugin instances as event listeners, so that plugins can listen for events such as
        // ServerStartEvent and ServerStopEvent in their main class.
        for (container in pluginManager.plugins) {
            val instance = container.instance ?: continue
            val eventNode = pluginManager.getEventNode(container)
            try {
                eventNode.registerListeners(instance)
            } catch (exception: Exception) {
                LOGGER.error("Unable to register plugin listener for plugin ${container.description.name}!", exception)
            }
        }

        LOGGER.info("Finished plugin loading! Loaded ${pluginManager.plugins.size} plugins.")
    }

    fun tick(startTime: Long) {
        scheduler.process()
        statusManager.tick(startTime)

        worldManager.worlds.values.forEach { it.tick() }
        tickDispatcher.updateAndAwait(startTime)

        val tickTime = System.currentTimeMillis() - startTime
        tickDispatcher.refreshThreads(tickTime)
    }

    private fun saveEverything(suppressLog: Boolean, forced: Boolean): Boolean {
        playerManager.saveAll()
        return worldManager.saveAllChunks(suppressLog, forced)
    }

    override fun stop() {
        if (!running) {
            // We are already shutting down, don't try to shut down again.
            return
        }

        try {
            running = false
            stopServer()
        } catch (exception: Throwable) {
            LOGGER.error("Error whilst attempting to stop the server!", exception)
        }
    }

    private fun stopServer() {
        // Stop server and shut down session manager (disconnecting all players)
        LOGGER.info("Starting shutdown for AquaMine version ${AquaPlatform.version}...")
        networkServer.stop()
        tickDispatcher.shutdown()

        // Save data
        LOGGER.info("Saving and disconnecting players...")
        playerManager.saveAll()
        playerManager.disconnectAll()

        LOGGER.info("Saving worlds...")
        worldManager.worlds.values.forEach { it.doNotSave = false }
        worldManager.saveAllChunks(false, false)
        worldManager.worlds.values.forEach {
            try {
                it.close()
            } catch (exception: IOException) {
                LOGGER.error("Error whilst trying to close world ${it.data.name}!", exception)
            }
        }

        // Shut down plugins and unregister listeners
        LOGGER.info("Shutting down plugins and unregistering listeners...")
        eventNode.fire(AquaServerStopEvent)

        // Say goodbye :)
        LOGGER.info("Goodbye")

        // Manually shut down Log4J 2 here so it doesn't shut down before we've finished logging
        LogManager.shutdown()
    }

    fun isProtected(world: AquaWorld, x: Int, z: Int, player: AquaPlayer): Boolean {
        if (world.dimension !== World.OVERWORLD) return false
        if (player.hasPermission(AquaPermission.BYPASS_SPAWN_PROTECTION.node)) return false
        if (config.world.spawnProtectionRadius <= 0) return false
        // TODO: This isn't right. This should use the heightmap pos if not in world bounds, but it'll do for now.
        val distanceX = abs(x - world.data.spawnX)
        val distanceZ = abs(z - world.data.spawnZ)
        val maxDistance = max(distanceX, distanceZ)
        return maxDistance <= config.world.spawnProtectionRadius
    }

    override fun generateSoundSeed(): Long = random.nextLong()

    companion object {

        private const val SAVE_PROFILE_CACHE_INTERVAL = 600

        private val LOGGER = LogManager.getLogger()
    }
}
