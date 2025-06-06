package net.aquamine.api.permission

import net.kyori.adventure.util.TriState

/**
 * A function that provides permission settings.
 */
fun interface PermissionFunction {

    /**
     * Gets the value for the given [permission].
     *
     * @param permission The permission.
     *
     * @return The value.
     */
    fun getPermissionValue(permission: String): TriState

    companion object {

        /**
         * A permission function that always returns [TriState.TRUE].
         */
        @JvmField
        val ALWAYS_TRUE: PermissionFunction = PermissionFunction { TriState.TRUE }

        /**
         * A permission function that always returns [TriState.FALSE].
         */
        @JvmField
        val ALWAYS_FALSE: PermissionFunction = PermissionFunction { TriState.FALSE }

        /**
         * A permission function that always returns [TriState.NOT_SET].
         */
        @JvmField
        val ALWAYS_NOT_SET: PermissionFunction = PermissionFunction { TriState.NOT_SET }
    }
}
