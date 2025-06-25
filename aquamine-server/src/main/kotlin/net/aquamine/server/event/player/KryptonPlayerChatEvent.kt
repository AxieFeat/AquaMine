package net.aquamine.server.event.player

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.PlayerChatEvent
import net.aquamine.api.event.type.AbstractDeniableEventWithResult

class KryptonPlayerChatEvent(
    override val player: Player,
    override val message: String
) : AbstractDeniableEventWithResult<PlayerChatEvent.Result>(), PlayerChatEvent
