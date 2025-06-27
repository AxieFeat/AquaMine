package net.aquamine.server.entity.components

import net.aquamine.api.entity.Hand
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.util.InteractionResult
import net.aquamine.server.world.AquaWorld

interface Interactable {

    fun interact(player: AquaPlayer, hand: Hand): InteractionResult = InteractionResult.PASS

    fun interactAt(player: AquaPlayer, x: Float, y: Float, z: Float, hand: Hand): InteractionResult = InteractionResult.PASS

    fun canInteract(world: AquaWorld, x: Int, y: Int, z: Int): Boolean = true
}
