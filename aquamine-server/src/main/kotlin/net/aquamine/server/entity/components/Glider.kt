package net.aquamine.server.entity.components

import net.aquamine.api.entity.ArmorSlot
import net.aquamine.api.entity.player.Player
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.event.player.action.KryptonPlayerStartGlidingEvent
import net.aquamine.server.event.player.action.KryptonPlayerStopGlidingEvent

interface Glider : BaseEntity, Player {

    override var isGliding: Boolean

    // This has vanilla logic in it that we don't want present in the API.
    fun tryStartGliding(): Boolean {
        // TODO: Check for levitation effect
        if (isOnGround || isGliding || isInWater()) return false
        val item = inventory.getArmor(ArmorSlot.CHESTPLATE)
        if (item.type == ItemTypes.ELYTRA && item.meta.damage < item.type.durability - 1) {
            startGliding()
            return true
        }
        return false
    }

    override fun startGliding(): Boolean {
        if (server.eventNode.fire(KryptonPlayerStartGlidingEvent(this)).isAllowed()) {
            isGliding = true
            return true
        }

        // Took this from Spigot. It seems like it's taken from the vanilla thing below, but if we don't have this,
        // it can cause issues like https://hub.spigotmc.org/jira/browse/SPIGOT-5542.
        isGliding = true
        isGliding = false
        return false
    }

    override fun stopGliding(): Boolean {
        if (!server.eventNode.fire(KryptonPlayerStopGlidingEvent(this)).isAllowed()) return false

        // This is a vanilla thing
        isGliding = true
        isGliding = false
        return true
    }
}
