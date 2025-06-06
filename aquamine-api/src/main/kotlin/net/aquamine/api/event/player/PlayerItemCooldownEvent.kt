package net.aquamine.api.event.player

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.event.type.DeniableEventWithResult
import net.aquamine.api.event.type.PlayerEvent
import net.aquamine.api.item.ItemType

/**
 * Called when a cooldown is set on usage of the given [item] for the given
 * [player].
 */
interface PlayerItemCooldownEvent : PlayerEvent, DeniableEventWithResult<PlayerItemCooldownEvent.Result> {

    /**
     * The item type that the cooldown is being applied to.
     */
    val item: ItemType

    /**
     * The time, in ticks, that the cooldown will be in effect for.
     */
    val cooldown: Int

    /**
     * The result of a cooldown event.
     *
     * This allows plugins to modify the actual cooldown that gets applied to the
     *
     * @property cooldown The resulting cooldown amount, in ticks.
     */
    @JvmRecord
    @ImmutableType
    data class Result(val cooldown: Int)
}
