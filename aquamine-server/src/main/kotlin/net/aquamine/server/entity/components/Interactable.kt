package net.aquamine.server.entity.components

import net.aquamine.api.entity.Hand
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.util.InteractionResult
import net.aquamine.server.world.KryptonWorld

interface Interactable {

    fun interact(player: KryptonPlayer, hand: Hand): InteractionResult = InteractionResult.PASS

    fun interactAt(player: KryptonPlayer, x: Float, y: Float, z: Float, hand: Hand): InteractionResult = InteractionResult.PASS

    fun canInteract(world: KryptonWorld, x: Int, y: Int, z: Int): Boolean = true
}
