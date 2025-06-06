package net.aquamine.api.event

/**
 * Used to indicate that the target function is a listener for an event.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Listener
