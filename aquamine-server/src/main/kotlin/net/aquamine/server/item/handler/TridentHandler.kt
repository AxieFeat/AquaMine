package net.aquamine.server.item.handler

import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.GameMode
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.state.AquaBlockState

object TridentHandler : ItemHandler {

    override fun canAttackBlock(player: AquaPlayer, world: AquaWorld, block: AquaBlockState, pos: Vec3i): Boolean =
        player.gameMode != GameMode.CREATIVE
}
