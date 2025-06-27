package net.aquamine.server.event.player.interact

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.Hand
import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.interact.PlayerInteractAtEntityEvent
import net.aquamine.api.event.type.AbstractDeniableEvent
import net.aquamine.api.util.Vec3d

class AquaPlayerInteractAtEntityEvent(
    override val player: Player,
    override val target: Entity,
    override val hand: Hand,
    override val clickedPosition: Vec3d
) : AbstractDeniableEvent(), PlayerInteractAtEntityEvent
