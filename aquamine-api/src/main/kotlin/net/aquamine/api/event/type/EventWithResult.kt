package net.aquamine.api.event.type

import net.aquamine.api.event.Event

/**
 * A type of event that can optionally return a result to modify the behavior
 * of the event.
 */
interface EventWithResult<T> : Event {

    /**
     * The result that is returned by this event.
     */
    var result: T?
}
