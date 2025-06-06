package net.aquamine.api.plugin

import java.nio.file.Path

/**
 * The plugin manager.
 */
interface PluginManager {

    /**
     * The list of currently loaded plugins.
     */
    val plugins: Collection<PluginContainer>

    /**
     * Get the plugin container for the specified plugin [instance], or null if
     * there isn't one.
     *
     * @param instance The plugin instance.
     *
     * @return The container for the instance, or null if there isn't one.
     */
    fun fromInstance(instance: Any): PluginContainer?

    /**
     * Get a plugin's container by its [id], or null if there isn't a plugin
     * loaded with the specified [id].
     *
     * @param id The id of the plugin.
     *
     * @return The plugin container with the specified [id], or null if there
     * isn't one.
     */
    fun getPlugin(id: String): PluginContainer?

    /**
     * Check if a plugin with the specified [id] is currently loaded.
     *
     * @param id the unique id of the plugin.
     *
     * @return `true` if there is a plugin with the name, and it is loaded, `false`
     * otherwise.
     */
    fun isLoaded(id: String): Boolean

    /**
     * Add the specified [path] to the server's classpath using the given
     * [plugin]'s class loader.
     *
     * @param plugin The plugin whose loader to load the [path] with.
     *
     * @param path The path to load.
     */
    fun addToClasspath(plugin: Any, path: Path)
}
