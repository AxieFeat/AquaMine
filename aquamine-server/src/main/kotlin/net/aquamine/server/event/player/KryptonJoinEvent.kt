package net.aquamine.server.event.player

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.PlayerJoinEvent
import net.aquamine.api.event.type.AbstractDeniableEventWithResult

class KryptonJoinEvent(
    override val player: Player,
    override val hasJoinedBefore: Boolean
) : AbstractDeniableEventWithResult<PlayerJoinEvent.Result>(), PlayerJoinEvent
