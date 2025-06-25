package net.aquamine.server.event.auth

import net.aquamine.api.event.player.PlayerAuthenticationEvent
import net.aquamine.api.event.type.AbstractDeniableEventWithResult

class KryptonPlayerAuthenticationEvent(
    override val username: String
) : AbstractDeniableEventWithResult<PlayerAuthenticationEvent.Result>(), PlayerAuthenticationEvent
