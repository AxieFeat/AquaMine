package net.aquamine.api.user

import java.util.UUID
import java.util.concurrent.CompletableFuture

/**
 * The manager for users.
 */
interface UserManager {

    /**
     * Gets the user with the given [uuid], or returns null if there is no user
     * with the given [uuid], or this user is not loaded.
     *
     * @param uuid The UUID of the user.
     *
     * @return The user with the UUID, or null if not present.
     */
    fun getUser(uuid: UUID): User?

    /**
     * Gets the user with the given [name], or returns null if there is no user
     * with the given [name], or this user is not loaded.
     *
     * @param name The last known name of the user.
     *
     * @return The user with the UUID, or null if not present.
     */
    fun getUser(name: String): User?

    /**
     * Gets the user with the given [uuid], loading them from persistent
     * storage if not already cached, or returns null if there is no user with
     * the given [uuid] available.
     *
     * @param uuid The UUID of the user.
     *
     * @return The user with the UUID, or null if not present.
     */
    fun loadUser(uuid: UUID): CompletableFuture<User?>

    /**
     * Gets the user with the given [name], loading them from persistent
     * storage if not already cached, or returns null if there is no user with
     * the given [name] available.
     *
     * @param name The last known name of the user.
     *
     * @return The user with the UUID, or null if not present.
     */
    fun loadUser(name: String): CompletableFuture<User?>
}
