package net.aquamine.api.event.entity

import net.aquamine.api.entity.Entity
import net.aquamine.api.event.type.DeniableEvent
import net.aquamine.api.event.type.EntityEvent
import net.aquamine.api.world.World

/**
 * Called when the given [entity] is removed from the given [world].
 */
interface RemoveEntityEvent : EntityEvent, DeniableEvent {

    /**
     * The entity that was removed from the world.
     */
    override val entity: Entity

    /**
     * The world that the entity was removed from.
     */
    val world: World
}
