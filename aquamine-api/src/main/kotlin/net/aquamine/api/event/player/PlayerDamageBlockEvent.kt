package net.aquamine.api.event.player

import net.aquamine.api.block.Block
import net.aquamine.api.event.type.DeniableEvent
import net.aquamine.api.event.type.PlayerEvent

/**
 * Called when a player damages a block.
 */
interface PlayerDamageBlockEvent : PlayerEvent, DeniableEvent {

    /**
     * The block that is being broken by the player.
     */
    val block: Block
}
