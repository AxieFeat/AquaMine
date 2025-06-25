package net.aquamine.server.item.handler

import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.GameMode
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.block.state.KryptonBlockState

object TridentHandler : ItemHandler {

    override fun canAttackBlock(player: KryptonPlayer, world: KryptonWorld, block: KryptonBlockState, pos: Vec3i): Boolean =
        player.gameMode != GameMode.CREATIVE
}
