package net.aquamine.server.event.player

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.EntityExitViewEvent

class AquaEntityExitViewEvent(override val player: Player, override val entity: Entity) : EntityExitViewEvent
