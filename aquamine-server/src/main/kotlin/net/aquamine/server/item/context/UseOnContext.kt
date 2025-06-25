package net.aquamine.server.item.context

import net.aquamine.api.entity.Hand
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.util.hit.BlockHitResult
import net.aquamine.server.world.KryptonWorld

open class UseOnContext protected constructor(
    val world: KryptonWorld,
    val player: KryptonPlayer?,
    val hand: Hand,
    val item: KryptonItemStack,
    protected val hitResult: BlockHitResult
) {

    constructor(player: KryptonPlayer, hand: Hand, hitResult: BlockHitResult) : this(player.world, player, hand, player.getHeldItem(hand), hitResult)

    open fun clickedPosition(): Vec3i = hitResult.position

    fun clickedFace(): Direction = hitResult.direction

    fun clickLocation(): Vec3d = hitResult.location

    fun isInside(): Boolean = hitResult.isInside

    open fun horizontalDirection(): Direction = player?.facing ?: Direction.NORTH

    open fun isSneaking(): Boolean = player != null && player.isSneaking

    open fun rotation(): Float {
        val position = player?.position ?: return 0F
        return position.pitch
    }
}
