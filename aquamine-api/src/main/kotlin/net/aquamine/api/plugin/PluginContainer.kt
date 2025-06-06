package net.aquamine.api.plugin

/**
 * A wrapper around a loaded plugin that may be injected to gain access to
 * some details that may otherwise be unavailable.
 */
interface PluginContainer {

    /**
     * The description of this plugin. This contains information that describes
     * this plugin.
     *
     * For more information, see [PluginDescription].
     */
    val description: PluginDescription

    /**
     * The instance of the main class that has been created and injected in to.
     *
     * This may be null in the case that this is injected in to the constructor
     * of your main class, due to the instance not being available until that
     * injection taking place.
     * This option here avoids the chicken and egg problem that would otherwise
     * ensue.
     */
    val instance: Any?
}
