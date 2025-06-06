package net.aquamine.api.event.player

import net.aquamine.api.event.type.PlayerEvent
import net.aquamine.api.resource.ResourcePack

/**
 * Called when the given [player] updates the server about the status of the
 * resource pack that the server has sent them.
 */
interface PlayerResourcePackStatusEvent : PlayerEvent {

    /**
     * The status of the player's resource pack.
     */
    val status: ResourcePack.Status
}
