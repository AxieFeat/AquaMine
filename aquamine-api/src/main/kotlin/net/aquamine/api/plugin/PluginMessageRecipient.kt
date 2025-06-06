package net.aquamine.api.plugin

import net.kyori.adventure.key.Key

/**
 * A recipient of plugin messages.
 */
fun interface PluginMessageRecipient {

    /**
     * Send a plugin message to this recipient on the specified [channel] with
     * the specified [message] content.
     *
     * @param channel The channel to send the message on.
     * @param message The message to send.
     */
    fun sendPluginMessage(channel: Key, message: ByteArray)
}
