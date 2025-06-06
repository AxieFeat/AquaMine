package net.aquamine.api.plugin.annotation

/**
 * Declares a dependency on another plugin.
 *
 * @param id The plugin ID of the **dependency**.
 * @param optional If this dependency is optional or not.
 */
@Target
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Dependency(val id: String, val optional: Boolean = false)
