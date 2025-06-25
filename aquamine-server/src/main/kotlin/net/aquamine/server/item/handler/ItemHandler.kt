package net.aquamine.server.item.handler

import net.aquamine.api.entity.Hand
import net.aquamine.api.util.Vec3i
import net.aquamine.server.util.InteractionResult
import net.aquamine.server.entity.KryptonLivingEntity
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.item.UseItemResult
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.block.state.KryptonBlockState

/**
 * A handler for a type of item.
 *
 * This helps promote sharing, as these can be reused between multiple item
 * type that share properties.
 */
interface ItemHandler {

    /**
     * Gets the destroy speed of the given [block] when destroyed with the
     * given [item].
     */
    fun destroySpeed(item: KryptonItemStack, block: KryptonBlockState): Float = 1F

    /**
     * Checks if this item type is the correct tool to break the given [block].
     */
    fun isCorrectTool(block: KryptonBlockState): Boolean = true

    /**
     * Checks if the given [player] can attack the given [block] at the given
     * [pos] in the given [world].
     */
    fun canAttackBlock(player: KryptonPlayer, world: KryptonWorld, block: KryptonBlockState, pos: Vec3i): Boolean = true

    fun interactEntity(item: KryptonItemStack, player: KryptonPlayer, entity: KryptonLivingEntity, hand: Hand): InteractionResult =
        InteractionResult.PASS

    /**
     * Called when the given [player] uses the item they are holding in the
     * given [hand].
     */
    fun use(player: KryptonPlayer, hand: Hand): UseItemResult = UseItemResult(InteractionResult.PASS, player.inventory.getHeldItem(hand))

    /**
     * Called when the given [player] finishes destroying the given [block] at
     * the given [pos] in the given [world], using the given [item] to
     * destroy it.
     */
    fun mineBlock(player: KryptonPlayer, item: KryptonItemStack, world: KryptonWorld, block: KryptonBlockState, pos: Vec3i): Boolean = false
}
