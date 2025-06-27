package net.aquamine.server.event.auth

import net.aquamine.api.event.player.PlayerAuthenticationEvent
import net.aquamine.api.event.type.AbstractDeniableEventWithResult

class AquaPlayerAuthenticationEvent(
    override val username: String
) : AbstractDeniableEventWithResult<PlayerAuthenticationEvent.Result>(), PlayerAuthenticationEvent
