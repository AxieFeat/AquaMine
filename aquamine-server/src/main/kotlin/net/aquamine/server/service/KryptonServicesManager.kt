package net.aquamine.server.service

import net.aquamine.api.plugin.PluginContainer
import net.aquamine.api.service.ServiceProvider
import net.aquamine.api.service.ServicesManager
import net.aquamine.server.KryptonServer
import java.util.concurrent.ConcurrentHashMap

class KryptonServicesManager(private val server: KryptonServer) : ServicesManager {

    private val providers = ConcurrentHashMap<Class<*>, KryptonServiceProvider<*>>()

    override fun <T> register(plugin: Any, type: Class<T>, service: T): ServiceProvider<T> {
        val container = requireNotNull(server.pluginManager.fromInstance(plugin)) { "Provided plugin $plugin is not a valid plugin instance!" }
        return register(container, type, service)
    }

    override fun <T> register(plugin: PluginContainer, type: Class<T>, service: T): ServiceProvider<T> {
        val provider = KryptonServiceProvider(plugin, type, service)
        providers.put(type, provider)
        return provider
    }

    override fun <T> provide(clazz: Class<T>): T? = getProvider(clazz)?.service

    @Suppress("UNCHECKED_CAST")
    override fun <T> getProvider(clazz: Class<T>): ServiceProvider<T>? = providers.get(clazz) as? ServiceProvider<T>
}
