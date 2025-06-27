package net.aquamine.server.event.player.action

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.action.PlayerStopSprintingEvent
import net.aquamine.api.event.type.AbstractDeniableEvent

class AquaPlayerStopSprintingEvent(override val player: Player) : AbstractDeniableEvent(), PlayerStopSprintingEvent
