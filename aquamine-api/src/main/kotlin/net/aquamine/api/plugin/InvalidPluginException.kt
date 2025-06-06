package net.aquamine.api.plugin

/**
 * Thrown when a plugin is invalid in some way.
 */
class InvalidPluginException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause)
