package net.aquamine.api.permission

import net.kyori.adventure.permission.PermissionChecker
import net.kyori.adventure.util.TriState

/**
 * An object that has a queryable set of permissions.
 */
fun interface Subject {

    /**
     * Gets the value for the given [permission].
     *
     * For some subtypes of this interface, this may always be a constant
     * value, generally indicating that the type does not possess permissions.
     *
     * @param permission The permission.
     *
     * @return The value for the given permission.
     */
    fun getPermissionValue(permission: String): TriState

    /**
     * Checks if this subject has the given [permission].
     *
     * A subject having a permission is defined as the subject both possessing
     * the permission, and it being set to [TriState.TRUE].
     *
     * @param permission The permission.
     *
     * @return `true` if this subject has the permission, `false` otherwise.
     */
    fun hasPermission(permission: String): Boolean = true

    /**
     * Converts this subject to its equivalent Adventure permission checker.
     *
     * @return The permission checker.
     */
    fun asPermissionChecker(): PermissionChecker = PermissionChecker { getPermissionValue(it) }
}
