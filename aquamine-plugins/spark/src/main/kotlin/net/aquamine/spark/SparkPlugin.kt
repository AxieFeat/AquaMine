package net.aquamine.spark

import com.google.inject.Inject
import me.lucko.spark.common.SparkPlatform
import me.lucko.spark.common.SparkPlugin
import me.lucko.spark.common.monitor.ping.PlayerPingProvider
import me.lucko.spark.common.platform.PlatformInfo
import me.lucko.spark.common.platform.serverconfig.ServerConfigProvider
import me.lucko.spark.common.platform.world.WorldInfoProvider
import me.lucko.spark.common.sampler.ThreadDumper
import me.lucko.spark.common.sampler.source.ClassSourceLookup
import me.lucko.spark.common.sampler.source.SourceMetadata
import me.lucko.spark.common.tick.TickHook
import me.lucko.spark.common.tick.TickReporter
import me.lucko.spark.common.util.classfinder.ClassFinder
import net.aquamine.api.Server
import net.aquamine.api.command.CommandMeta
import net.aquamine.api.event.Listener
import net.aquamine.api.event.server.ServerStartEvent
import net.aquamine.api.event.server.ServerStopEvent
import net.aquamine.api.plugin.PluginDescription
import net.aquamine.api.plugin.annotation.DataFolder
import net.aquamine.spark.provider.AquaPlayerPingProvider
import net.aquamine.spark.provider.AquaServerConfigProvider
import net.aquamine.spark.provider.world.AquaWorldInfoProvider
import net.aquamine.spark.tick.AquaTickHook
import net.aquamine.spark.tick.AquaTickReporter
import org.apache.logging.log4j.Logger
import java.nio.file.Path
import java.util.logging.Level
import java.util.stream.Stream

class SparkPlugin @Inject constructor(
    val server: Server,
    val logger: Logger,
    @DataFolder
    val dataFolder: Path,
    val description: PluginDescription
) : SparkPlugin {

    private val platformInfo = AquaPlatformInfo(server)

    private lateinit var platform: SparkPlatform

    private val threadDumper = ThreadDumper.Regex(setOf("^AquaMine.*"))

    @Listener
    fun onStart(event: ServerStartEvent) {
        this.platform = SparkPlatform(this)

        server.commandManager.register(
            SparkCommand(platform),
            CommandMeta.builder(description.id).build()
        )

        this.platform.enable()
    }

    @Listener
    fun onStop(event: ServerStopEvent) {
        this.platform.disable()
    }

    override fun getVersion(): String = description.version

    override fun getPluginDirectory(): Path = dataFolder

    override fun getCommandName(): String = description.id

    override fun getCommandSenders(): Stream<AquaCommandSender> {
        return server.players.map { AquaCommandSender(it) }.stream()
    }

    override fun executeAsync(task: Runnable) {
        server.scheduler.buildTask(task).async().schedule()
    }

    override fun getPlatformInfo(): PlatformInfo = platformInfo

    override fun log(level: Level, msg: String) {
        if (level.intValue() >= 1000) { // severe
            logger.error(msg)
        } else if (level.intValue() >= 900) { // warning
            logger.warn(msg)
        } else {
            logger.info(msg)
        }
    }

    override fun log(level: Level, msg: String, throwable: Throwable) {
        if (level.intValue() >= 1000) { // severe
            logger.error(msg, throwable)
        } else if (level.intValue() >= 900) { // warning
            logger.warn(msg, throwable)
        } else {
            logger.info(msg, throwable)
        }
    }

    override fun createClassSourceLookup(): ClassSourceLookup {
        return AquaClassSourceLookup(server)
    }

    override fun createPlayerPingProvider(): PlayerPingProvider {
        return AquaPlayerPingProvider(server)
    }

    override fun createTickHook(): TickHook {
        return AquaTickHook(server)
    }

    override fun createTickReporter(): TickReporter {
        return AquaTickReporter(server)
    }

    override fun createWorldInfoProvider(): WorldInfoProvider {
        return AquaWorldInfoProvider(server)
    }

    override fun createServerConfigProvider(): ServerConfigProvider {
        return AquaServerConfigProvider()
    }

    override fun createClassFinder(): ClassFinder {
        return AquaClassFinder()
    }

    override fun getDefaultThreadDumper(): ThreadDumper {
        return threadDumper
    }

    override fun getKnownSources(): Collection<SourceMetadata> {
        return server.pluginManager.plugins.map {
            val description = it.description

            SourceMetadata(
                description.id,
                description.version,
                description.authors.joinToString(", "),
                description.description,
            )
        }
    }

}