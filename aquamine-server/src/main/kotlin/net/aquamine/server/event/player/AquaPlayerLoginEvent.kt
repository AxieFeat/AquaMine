package net.aquamine.server.event.player

import net.aquamine.api.auth.GameProfile
import net.aquamine.api.event.player.PlayerLoginEvent
import net.aquamine.api.event.type.AbstractDeniableEventWithResult
import java.net.InetSocketAddress

class AquaPlayerLoginEvent(
    override val profile: GameProfile,
    override val address: InetSocketAddress
) : AbstractDeniableEventWithResult<PlayerLoginEvent.Result>(), PlayerLoginEvent
