package net.aquamine.server

import net.aquamine.api.event.GlobalEventNode
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.util.FactoryProvider
import net.aquamine.server.command.AquaCommandManager
import net.aquamine.server.console.AquaConsole
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.plugin.AquaPluginManager
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.scheduling.AquaScheduler
import net.aquamine.server.service.AquaServicesManager
import net.aquamine.server.user.AquaUserManager
import net.aquamine.server.util.AquaFactoryProvider
import net.aquamine.server.world.AquaWorldManager
import java.util.UUID

interface BaseServer : ServerAudience {

    override val console: AquaConsole

    override val worldManager: AquaWorldManager
    override val commandManager: AquaCommandManager
    override val pluginManager: AquaPluginManager
    override val eventNode: GlobalEventNode
    override val servicesManager: AquaServicesManager
    override val scheduler: AquaScheduler
    override val userManager: AquaUserManager

    override val platform: AquaPlatform
        get() = AquaPlatform
    override val registryHolder: RegistryHolder
        get() = AquaRegistries.StaticHolder
    override val factoryProvider: FactoryProvider
        get() = AquaFactoryProvider

    fun isRunning(): Boolean

    fun stop()

    override fun getPlayer(name: String): AquaPlayer? = playerManager.getPlayer(name)

    override fun getPlayer(uuid: UUID): AquaPlayer? = playerManager.getPlayer(uuid)
}
