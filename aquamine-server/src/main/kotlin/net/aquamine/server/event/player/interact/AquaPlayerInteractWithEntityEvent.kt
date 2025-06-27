package net.aquamine.server.event.player.interact

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.Hand
import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.interact.PlayerInteractWithEntityEvent
import net.aquamine.api.event.type.AbstractDeniableEvent

class AquaPlayerInteractWithEntityEvent(
    override val player: Player,
    override val target: Entity,
    override val hand: Hand
) : AbstractDeniableEvent(), PlayerInteractWithEntityEvent
