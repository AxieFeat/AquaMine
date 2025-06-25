package net.aquamine.server.event.player

import net.aquamine.api.block.Block
import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.PlayerDamageBlockEvent
import net.aquamine.api.event.type.AbstractDeniableEvent

class KryptonPlayerDamageBlockEvent(override val player: Player, override val block: Block) : AbstractDeniableEvent(), PlayerDamageBlockEvent
