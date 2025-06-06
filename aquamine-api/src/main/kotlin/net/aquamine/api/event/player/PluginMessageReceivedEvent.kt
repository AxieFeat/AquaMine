package net.aquamine.api.event.player

import net.kyori.adventure.key.Key
import net.aquamine.api.event.type.PlayerEvent

/**
 * Called when a plugin message is received from a client.
 */
interface PluginMessageReceivedEvent : PlayerEvent {

    /**
     * The channel that the message was received on.
     */
    val channel: Key

    /**
     * The message that was received from the sender.
     */
    val message: ByteArray
}
