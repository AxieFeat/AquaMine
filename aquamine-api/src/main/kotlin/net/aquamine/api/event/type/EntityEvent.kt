package net.aquamine.api.event.type

import net.aquamine.api.entity.Entity
import net.aquamine.api.event.Event

/**
 * An event for an entity.
 */
interface EntityEvent : Event {

    /**
     * The entity that the event is for.
     */
    val entity: Entity
}
