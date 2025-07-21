package net.aquamine.api.world.gameevent

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed

/**
 * Represents game event for sculk sensor.
 */
@CataloguedBy(GameEvents::class)
@ImmutableType
interface GameEvent : Keyed {

    /**
     * The radius of this event.
     */
    val radius: Int

}