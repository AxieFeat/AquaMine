package net.aquamine.server.plugin.module

import com.google.inject.name.Names
import dev.misfitlabs.kotlinguice4.KotlinModule
import net.aquamine.api.Platform
import net.aquamine.api.Server
import net.aquamine.api.command.CommandManager
import net.aquamine.api.command.ConsoleSender
import net.aquamine.api.event.GlobalEventNode
import net.aquamine.api.plugin.PluginContainer
import net.aquamine.api.plugin.PluginManager
import net.aquamine.api.scheduling.Scheduler
import net.aquamine.api.service.ServicesManager
import net.aquamine.api.user.UserManager
import net.aquamine.api.util.FactoryProvider
import net.aquamine.api.world.WorldManager
import net.aquamine.server.AquaServer

class GlobalModule(private val server: AquaServer, private val pluginContainers: Collection<PluginContainer>) : KotlinModule() {

    override fun configure() {
        bind<Server>().toInstance(server)
        bind<Platform>().toInstance(server.platform)
        bind<WorldManager>().toInstance(server.worldManager)
        bind<CommandManager>().toInstance(server.commandManager)
        bind<PluginManager>().toInstance(server.pluginManager)
        bind<ServicesManager>().toInstance(server.servicesManager)
        bind<GlobalEventNode>().toInstance(server.eventNode)
        bind<FactoryProvider>().toInstance(server.factoryProvider)
        bind<UserManager>().toInstance(server.userManager)
        bind<Scheduler>().toInstance(server.scheduler)
        bind<ConsoleSender>().toInstance(server.console)
        pluginContainers.forEach { bind<PluginContainer>().annotatedWith(Names.named(it.description.id)).toInstance(it) }
    }
}
