package net.aquamine.api.service

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.plugin.PluginContainer

/**
 * Represents a provider of a service of type [T].
 *
 * Plugins can use these to provide classes to other plugins in a way that
 * allows them to not need to know who they are actually providing the service
 * to (if anyone), which is a neat abstraction layer.
 */
@ImmutableType
interface ServiceProvider<T> {

    /**
     * The plugin that provided this service.
     */
    val plugin: PluginContainer

    /**
     * The class of the service being provided.
     */
    val type: Class<T>

    /**
     * The service provided.
     */
    val service: T
}
