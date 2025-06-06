package net.aquamine.api.event.type

/**
 * A type of event that combines the behavior of [DeniableEvent] and
 * [EventWithResult].
 */
interface DeniableEventWithResult<T> : DeniableEvent, EventWithResult<T> {

    /**
     * Allows this event to occur, and sets the result to the given [result].
     *
     * This is a shortcut for calling [allow] and setting the [EventWithResult.result]
     * manually.
     *
     * @param result The result to set.
     */
    fun allowWithResult(result: T) {
        allow()
        this.result = result
    }

    /**
     * Denies this event from occur, and sets the result to the given [result].
     *
     * This is a shortcut for calling [deny] and setting the [EventWithResult.result]
     * manually.
     *
     * @param result The result to set.
     */
    fun denyWithResult(result: T) {
        deny()
        this.result = result
    }
}
