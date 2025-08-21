package net.aquamine.api.entity

import net.aquamine.api.entity.player.Player

/**
 * An lightning bolt.
 */
interface LightningBolt : Entity {

    /**
     * Whether the strike is an effect that does no damage.
     */
    var isEffect: Boolean

    /**
     * Get the number of ticks this lightning strike will inflict damage upon its hit entities.
     *
     * When life ticks are negative, there is a random chance that another
     * flash will be initiated and life ticks reset to 1. Additionally, if
     * life ticks are set to 2 (the default value when a lightning strike
     * has been spawned), a list of events will occur:
     * - Impact sound effects will be played
     * - Fire will be spawned (dependent on difficulty)
     * - Lightning rods will be powered (if hit)
     * - Copper will be stripped (if hit)
     * - [net.aquamine.api.world.gameevent.GameEvents.LIGHTNING_STRIKE] will be dispatched
     */
    var lifeTicks: Int

    /**
     * The number of flash iterations that will be done before the lightning dies.
     * The default number of flashes on creation is between 1-3.
     */
    var flashCount: Int

    /**
     * The [Player] that caused this lightning to strike.
     */
    var causingPlayer: Player?
}
