package net.aquamine.server.event.player

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.PlayerResourcePackStatusEvent
import net.aquamine.api.resource.ResourcePack

class KryptonPlayerResourcePackStatusEvent(override val player: Player, override val status: ResourcePack.Status) : PlayerResourcePackStatusEvent
