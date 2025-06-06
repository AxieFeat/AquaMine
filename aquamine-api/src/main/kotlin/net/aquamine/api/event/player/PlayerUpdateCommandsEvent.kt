package net.aquamine.api.event.player

import com.mojang.brigadier.tree.RootCommandNode
import net.aquamine.api.event.type.PlayerEvent

/**
 * Called when the server updates the available commands for the player.
 */
interface PlayerUpdateCommandsEvent : PlayerEvent {

    /**
     * The root of the command tree that will be sent.
     *
     * This can be mutated to change the command tree that is sent to the
     * player.
     */
    val rootNode: RootCommandNode<*>
}
