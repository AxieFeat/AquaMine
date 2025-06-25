package net.aquamine.server.event.player

import net.kyori.adventure.key.Key
import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.PluginMessageReceivedEvent

class KryptonPluginMessageReceivedEvent(
    override val player: Player,
    override val channel: Key,
    override val message: ByteArray
) : PluginMessageReceivedEvent
