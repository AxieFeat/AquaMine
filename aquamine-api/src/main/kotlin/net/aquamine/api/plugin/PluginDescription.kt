package net.aquamine.api.plugin

import net.aquamine.annotations.ImmutableType
import java.nio.file.Path

/**
 * Represents the plugin.conf file that every plugin has.
 *
 * This holds details about the plugin. For example, it's a name, main class,
 * version, description, authors and dependencies.
 */
@ImmutableType
interface PluginDescription {

    /**
     * The unique ID of this plugin.
     */
    val id: String

    /**
     * The name of this plugin. Defaults to empty.
     */
    val name: String

    /**
     * The version of this plugin. Defaults to <UNDEFINED>.
     */
    val version: String

    /**
     * A short description of this plugin.
     */
    val description: String

    /**
     * The list of people who created this plugin.
     */
    val authors: Collection<String>

    /**
     * The list of dependencies of this plugin.
     */
    val dependencies: Collection<PluginDependency>

    /**
     * The source path that this plugin was loaded from, or null if this plugin
     * was not loaded from a file.
     */
    val source: Path?

    /**
     * Gets the plugin dependency with the given [id], or returns null if there
     * is no dependency with the given [id].
     *
     * @param id The ID of the dependency.
     *
     * @return The dependency, or null if not present.
     */
    fun getDependency(id: String): PluginDependency?
}
