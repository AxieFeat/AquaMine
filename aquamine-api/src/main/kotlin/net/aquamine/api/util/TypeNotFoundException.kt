package net.aquamine.api.util

import java.io.Serial

/**
 * Thrown when a factory requested from the factory provider was not found.
 */
class TypeNotFoundException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    companion object {

        @Serial
        private const val serialVersionUID = 7032005516854864359L
    }
}
