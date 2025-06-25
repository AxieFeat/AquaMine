package net.aquamine.server.event.server

import net.aquamine.api.event.server.SetupPermissionsEvent
import net.aquamine.api.event.type.AbstractEventWithResult
import net.aquamine.api.permission.PermissionFunction
import net.aquamine.api.permission.Subject

class KryptonSetupPermissionsEvent(
    override val subject: Subject,
    override val defaultFunction: PermissionFunction
) : AbstractEventWithResult<SetupPermissionsEvent.Result>(), SetupPermissionsEvent
