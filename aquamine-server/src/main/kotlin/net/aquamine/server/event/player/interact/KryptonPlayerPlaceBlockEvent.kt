package net.aquamine.server.event.player.interact

import net.aquamine.api.block.BlockState
import net.aquamine.api.entity.Hand
import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.interact.PlayerPlaceBlockEvent
import net.aquamine.api.event.type.AbstractDeniableEvent
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i

class KryptonPlayerPlaceBlockEvent(
    override val player: Player,
    override val block: BlockState,
    override val hand: Hand,
    override val position: Vec3i,
    override val face: Direction,
    override val isInsideBlock: Boolean
) : AbstractDeniableEvent(), PlayerPlaceBlockEvent
