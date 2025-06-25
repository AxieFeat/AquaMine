package net.aquamine.server.event.player.action

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.action.PlayerStartSprintingEvent
import net.aquamine.api.event.type.AbstractDeniableEvent

class KryptonPlayerStartSprintingEvent(override val player: Player) : AbstractDeniableEvent(), PlayerStartSprintingEvent
