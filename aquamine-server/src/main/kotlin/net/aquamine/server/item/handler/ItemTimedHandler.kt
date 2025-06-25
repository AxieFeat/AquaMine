package net.aquamine.server.item.handler

import net.aquamine.api.entity.Hand
import net.aquamine.server.item.UseItemResult
import net.aquamine.server.util.InteractionResult
import net.aquamine.server.entity.player.KryptonPlayer

interface ItemTimedHandler : ItemHandler {

    fun finishUse(player: KryptonPlayer, hand: Hand): UseItemResult = UseItemResult(InteractionResult.PASS, player.inventory.getHeldItem(hand))
}
