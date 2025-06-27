package net.aquamine.server.event.player.interact

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.interact.PlayerAttackEntityEvent
import net.aquamine.api.event.type.AbstractDeniableEvent

class AquaPlayerAttackEntityEvent(override val player: Player, override val target: Entity) : AbstractDeniableEvent(), PlayerAttackEntityEvent
