package net.aquamine.server.event.player

import com.mojang.brigadier.tree.RootCommandNode
import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.PlayerUpdateCommandsEvent

class AquaPlayerUpdateCommandsEvent(override val player: Player, override val rootNode: RootCommandNode<*>) : PlayerUpdateCommandsEvent
