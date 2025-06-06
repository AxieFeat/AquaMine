package net.aquamine.api.event.type

import net.aquamine.api.event.Event

/**
 * A type of event that can be allowed or denied from occurring.
 */
interface DeniableEvent : Event {

    /**
     * Checks if this event is allowed to happen.
     *
     * This is equivalent to standard event cancellation that may be more
     * familiar to developers, except that it is the other way around.
     * This property indicates whether an event is allowed to occur, rather
     * than if an event is not allowed to occur (canceled).
     *
     * This will default to true, and can be updated with [allow] and [deny].
     */
    fun isAllowed(): Boolean

    /**
     * Allows this event to occur.
     *
     * An event is, by default, allowed to occur, and so it is not necessary
     * to call this method to indicate this, unless you wish to allow an event
     * that has been denied.
     */
    fun allow()

    /**
     * Denies an event from occurring.
     */
    fun deny()
}
