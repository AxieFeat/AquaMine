package net.aquamine.api.event.entity

import net.aquamine.api.entity.Entity
import net.aquamine.api.event.type.DeniableEvent
import net.aquamine.api.event.type.EntityEvent
import net.aquamine.api.world.World

/**
 * Called when the given [entity] spawns in to the given [world].
 */
interface SpawnEntityEvent : EntityEvent, DeniableEvent {

    /**
     * The entity that is being spawned in to the world.
     */
    override val entity: Entity

    /**
     * The world that the entity is being spawned in to.
     */
    val world: World
}
