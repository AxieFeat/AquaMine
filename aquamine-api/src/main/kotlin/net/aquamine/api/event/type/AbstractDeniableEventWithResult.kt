package net.aquamine.api.event.type

/**
 * A skeletal implementation of [DeniableEventWithResult].
 */
abstract class AbstractDeniableEventWithResult<T> : AbstractDeniableEvent(), DeniableEventWithResult<T> {

    override var result: T? = null
}
