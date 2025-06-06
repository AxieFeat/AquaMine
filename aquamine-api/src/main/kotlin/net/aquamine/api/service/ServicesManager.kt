@file:JvmSynthetic
package net.aquamine.api.service

import net.aquamine.api.plugin.PluginContainer

/**
 * The manager of services.
 */
interface ServicesManager {

    /**
     * Gets the service for the given [clazz], or returns null if there is no
     * service registered for the given [clazz].
     *
     * @param T The service type.
     * @param clazz The service class.
     *
     * @return The service, or null if not present.
     */
    fun <T> provide(clazz: Class<T>): T?

    /**
     * Gets the service provider for the service of the given [clazz] type, or
     * returns null if there is no service provider for the given [clazz].
     *
     * @param T The service type.
     * @param clazz The service class.
     *
     * @return The service provider, or null if not present.
     */
    fun <T> getProvider(clazz: Class<T>): ServiceProvider<T>?

    /**
     * Registers a new service to this service's manager.
     *
     * @param T The service type.
     * @param plugin The plugin that registered the service.
     * @param type The type of the service being provided.
     * @param service The service being provided.
     *
     * @return The registered service provider.
     */
    fun <T> register(plugin: Any, type: Class<T>, service: T): ServiceProvider<T>

    /**
     * Registers a new service to this service's manager.
     *
     * @param T The service type.
     * @param plugin The plugin that registered the service.
     * @param type The type of the service being provided.
     * @param service The service being provided.
     *
     * @return The registered service provider.
     */
    fun <T> register(plugin: PluginContainer, type: Class<T>, service: T): ServiceProvider<T>
}
