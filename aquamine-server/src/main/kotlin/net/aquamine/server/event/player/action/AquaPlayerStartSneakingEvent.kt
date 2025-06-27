package net.aquamine.server.event.player.action

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.action.PlayerStartSneakingEvent
import net.aquamine.api.event.type.AbstractDeniableEvent

class AquaPlayerStartSneakingEvent(override val player: Player) : AbstractDeniableEvent(), PlayerStartSneakingEvent
