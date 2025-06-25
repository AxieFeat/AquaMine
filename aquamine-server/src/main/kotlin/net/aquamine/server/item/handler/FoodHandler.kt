package net.aquamine.server.item.handler

import net.aquamine.api.entity.Hand
import net.aquamine.server.util.InteractionResult
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.item.UseItemResult

object FoodHandler : ItemTimedHandler {

    private const val DUMMY_FOOD_LEVEL_INCREASE = 8
    private const val DUMMY_SATURATION_LEVEL_INCREASE = 12.8F

    override fun finishUse(player: KryptonPlayer, hand: Hand): UseItemResult {
        // TODO: Remove hardcoded values and add tick system
        player.inventory.setHeldItem(hand, player.inventory.getHeldItem(hand).shrink(1))
        // These are dummy values for testing, until saturation and food level values
        // can be pulled from item definitions, and once more thought is in put into
        // fleshing out the handling of food consumption, etc.
        player.foodLevel += DUMMY_FOOD_LEVEL_INCREASE
        player.foodSaturationLevel += DUMMY_SATURATION_LEVEL_INCREASE
        return UseItemResult(InteractionResult.PASS, player.inventory.getHeldItem(hand))
    }
}
