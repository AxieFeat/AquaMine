package net.aquamine.server.event.player.action

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.action.PlayerStopSneakingEvent
import net.aquamine.api.event.type.AbstractDeniableEvent

class KryptonPlayerStopSneakingEvent(override val player: Player) : AbstractDeniableEvent(), PlayerStopSneakingEvent
