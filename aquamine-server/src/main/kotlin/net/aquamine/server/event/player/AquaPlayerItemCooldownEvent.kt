package net.aquamine.server.event.player

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.PlayerItemCooldownEvent
import net.aquamine.api.event.type.AbstractDeniableEventWithResult
import net.aquamine.api.item.ItemType

class AquaPlayerItemCooldownEvent(
    override val player: Player,
    override val item: ItemType,
    override val cooldown: Int
) : AbstractDeniableEventWithResult<PlayerItemCooldownEvent.Result>(), PlayerItemCooldownEvent
