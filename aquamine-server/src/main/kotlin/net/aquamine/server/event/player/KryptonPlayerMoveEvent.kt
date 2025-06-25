package net.aquamine.server.event.player

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.PlayerMoveEvent
import net.aquamine.api.event.type.AbstractDeniableEventWithResult
import net.aquamine.api.util.Position

class KryptonPlayerMoveEvent(
    override val player: Player,
    override val oldPosition: Position,
    override val newPosition: Position
) : AbstractDeniableEventWithResult<PlayerMoveEvent.Result>(), PlayerMoveEvent
