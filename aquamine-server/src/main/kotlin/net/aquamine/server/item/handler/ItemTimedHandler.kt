package net.aquamine.server.item.handler

import net.aquamine.api.entity.Hand
import net.aquamine.server.item.UseItemResult
import net.aquamine.server.util.InteractionResult
import net.aquamine.server.entity.player.AquaPlayer

interface ItemTimedHandler : ItemHandler {

    fun finishUse(player: AquaPlayer, hand: Hand): UseItemResult = UseItemResult(InteractionResult.PASS, player.inventory.getHeldItem(hand))
}
