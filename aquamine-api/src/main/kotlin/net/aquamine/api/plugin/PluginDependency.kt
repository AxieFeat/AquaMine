package net.aquamine.api.plugin

import net.aquamine.annotations.ImmutableType

/**
 * Information for a plugin's dependency on another plugin.
 */
@ImmutableType
interface PluginDependency {

    /**
     * The plugin ID of the **dependency**, not the **dependent**.
     */
    val id: String

    /**
     * If the dependency is optional or not.
     */
    val isOptional: Boolean
}
