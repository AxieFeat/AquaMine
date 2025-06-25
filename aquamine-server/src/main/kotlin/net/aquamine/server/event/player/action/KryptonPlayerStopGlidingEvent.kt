package net.aquamine.server.event.player.action

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.action.PlayerStopGlidingEvent
import net.aquamine.api.event.type.AbstractDeniableEvent

class KryptonPlayerStopGlidingEvent(override val player: Player) : AbstractDeniableEvent(), PlayerStopGlidingEvent
