package net.aquamine.api.plugin.annotation

import org.intellij.lang.annotations.Pattern

/**
 * A metadata annotation used to describe a plugin.
 *
 * This has to go on the plugin's main class, and it will be the injection
 * point for the plugin. This is similar in functionality to the standard JVM
 * entry point of the [`main` method](https://docs.oracle.com/javase/tutorial/getStarted/application/index.html).
 *
 * @param id The identifier for this plugin.
 * @param name The human-readable name of this plugin.
 * @param version The version of this plugin, or empty for undefined.
 * @param description The description of this plugin, explaining what it can be
 * used for.
 * @param authors A list of people who helped create this plugin.
 * @param dependencies A list of other plugins that this plugin depends on.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Plugin(
    @Pattern(ID_REGEX) val id: String,
    val name: String = "",
    val version: String = "",
    val description: String = "",
    val authors: Array<String> = [],
    val dependencies: Array<Dependency> = []
) {

    companion object {

        /**
         * A regex for validating that your plugin ID is fine.
         *
         * If you want to check this on the web, it is recommended to copy this
         * in to [regex101](https://regex101.com) and then put in your plugin
         * ID and see if it matches.
         */
        const val ID_REGEX: String = "[a-z][a-z0-9-_]{0,63}"
    }
}
