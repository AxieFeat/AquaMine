@file:JvmSynthetic
package net.aquamine.api.service

import net.aquamine.api.plugin.PluginContainer

/**
 * Gets the service for the given type [T], or returns null if there is no
 * registered service for the given type [T].
 *
 * @param T The service type.
 *
 * @return The service, or null if not present.
 */
@JvmSynthetic
inline fun <reified T> ServicesManager.provide(): T? = provide(T::class.java)

/**
 * Gets the provider for the given type [T], or returns null if there is no
 * provider for the given type [T].
 *
 * @param T The service type.
 *
 * @return The service provider, or null if not present.
 */
@JvmSynthetic
inline fun <reified T> ServicesManager.getProvider(): ServiceProvider<T>? = getProvider(T::class.java)

/**
 * Registers a new service to this service's manager.
 *
 * @param T The service type.
 * @param plugin The plugin that registered the service.
 * @param service The service.
 */
@JvmSynthetic
inline fun <reified T> ServicesManager.register(plugin: PluginContainer, service: T) {
    register(plugin, T::class.java, service)
}

/**
 * Registers a new service to this service's manager.
 *
 * @param T The service type.
 * @param plugin The plugin that registered the service.
 *
 * @param service The service.
 */
@JvmSynthetic
inline fun <reified T> ServicesManager.register(plugin: Any, service: T) {
    register(plugin, T::class.java, service)
}
