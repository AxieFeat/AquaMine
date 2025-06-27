package net.aquamine.server.item.context

import net.aquamine.api.entity.Hand
import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.util.hit.BlockHitResult
import net.aquamine.server.world.AquaWorld

open class BlockPlaceContext protected constructor(
    world: AquaWorld,
    player: AquaPlayer?,
    hand: Hand,
    item: AquaItemStack,
    hitResult: BlockHitResult
) : UseOnContext(world, player, hand, item, hitResult) {

    private val relativePosition = hitResult.position.relative(hitResult.direction)
    protected var replaceClicked = world.getBlock(hitResult.position).canBeReplaced(this)

    override fun clickedPosition(): Vec3i = if (replaceClicked) super.clickedPosition() else relativePosition

    open fun canPlace(): Boolean = replaceClicked || world.getBlock(clickedPosition()).canBeReplaced(this)

    open fun replacingClickedOnBlock(): Boolean = replaceClicked
}
