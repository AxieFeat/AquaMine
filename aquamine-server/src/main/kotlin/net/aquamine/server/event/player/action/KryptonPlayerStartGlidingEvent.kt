package net.aquamine.server.event.player.action

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.action.PlayerStartGlidingEvent
import net.aquamine.api.event.type.AbstractDeniableEvent

class KryptonPlayerStartGlidingEvent(override val player: Player) : AbstractDeniableEvent(), PlayerStartGlidingEvent
