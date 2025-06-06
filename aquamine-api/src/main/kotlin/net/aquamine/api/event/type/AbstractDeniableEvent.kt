package net.aquamine.api.event.type

/**
 * A skeletal implementation of [DeniableEvent].
 */
abstract class AbstractDeniableEvent : DeniableEvent {

    private var allowed = true

    override fun isAllowed(): Boolean = allowed

    override fun allow() {
        allowed = true
    }

    override fun deny() {
        allowed = false
    }
}
