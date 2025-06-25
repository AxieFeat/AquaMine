package net.aquamine.server

import net.aquamine.api.event.GlobalEventNode
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.util.FactoryProvider
import net.aquamine.server.command.KryptonCommandManager
import net.aquamine.server.console.AquaConsole
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.plugin.KryptonPluginManager
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.scheduling.KryptonScheduler
import net.aquamine.server.service.KryptonServicesManager
import net.aquamine.server.user.KryptonUserManager
import net.aquamine.server.util.KryptonFactoryProvider
import net.aquamine.server.world.KryptonWorldManager
import java.util.UUID

interface BaseServer : ServerAudience {

    override val console: AquaConsole

    override val worldManager: KryptonWorldManager
    override val commandManager: KryptonCommandManager
    override val pluginManager: KryptonPluginManager
    override val eventNode: GlobalEventNode
    override val servicesManager: KryptonServicesManager
    override val scheduler: KryptonScheduler
    override val userManager: KryptonUserManager

    override val platform: KryptonPlatform
        get() = KryptonPlatform
    override val registryHolder: RegistryHolder
        get() = KryptonRegistries.StaticHolder
    override val factoryProvider: FactoryProvider
        get() = KryptonFactoryProvider

    fun isRunning(): Boolean

    fun stop()

    override fun getPlayer(name: String): KryptonPlayer? = playerManager.getPlayer(name)

    override fun getPlayer(uuid: UUID): KryptonPlayer? = playerManager.getPlayer(uuid)
}
