package net.aquamine.api.event.server

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.event.type.EventWithResult
import net.aquamine.api.permission.PermissionFunction
import net.aquamine.api.permission.Subject

/**
 * Called when a subject's permissions are initially being set up.
 */
interface SetupPermissionsEvent : EventWithResult<SetupPermissionsEvent.Result> {

    /**
     * The subject having their permissions set up.
     */
    val subject: Subject

    /**
     * The default permission provider that will be used if no plugin
     * specifies one by setting the result of this event.
     */
    val defaultFunction: PermissionFunction

    /**
     * The result of a setup permissions event.
     *
     * This is used to modify the function used to provide permissions
     * for the subject.
     *
     * @param function The permission function to use.
     */
    @JvmRecord
    @ImmutableType
    data class Result(val function: PermissionFunction)
}
