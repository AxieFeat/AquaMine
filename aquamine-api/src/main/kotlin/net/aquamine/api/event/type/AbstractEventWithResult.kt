package net.aquamine.api.event.type

/**
 * A skeletal implementation of [EventWithResult].
 */
abstract class AbstractEventWithResult<T> : EventWithResult<T> {

    override var result: T? = null
}
