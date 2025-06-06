package net.aquamine.api.event

import java.util.function.Consumer
import java.util.function.Function

/**
 * Creates a new event filter for the given event type [E] and handler
 * type [H] on the event, using the given [handlerGetter], if given, to get
 * the handler object from the event.
 *
 * If the handler getter is null, [EventFilter.getHandler] on the filter will
 * always return null.
 *
 * @param E The type of event to filter for.
 * @param H The type of handler object to filter from the event.
 * @param handlerGetter The function to get the handler object from the event.
 *
 * @return The event filter.
 */
inline fun <reified E : Event, reified H> EventFilter.Companion.create(handlerGetter: Function<E, H>?): EventFilter<E, H> {
    return create(E::class.java, H::class.java, handlerGetter)
}

/**
 * Registers an event listener for the given event type [T] to this event
 * node, calling the given [handler] when the event is fired.
 *
 * @param T The type of event the node accepts.
 * @param E The type of event to listen for.
 * @param handler The handler for the event.
 *
 * @return This event node.
 */
inline fun <T : Event, reified E : T> EventNode<T>.registerListener(handler: Consumer<E>) {
    registerListener(E::class.java, handler)
}

/**
 * Creates a new event listener builder for events of the
 * given event type [T].
 *
 * @param T The type of event.
 *
 * @return A new builder.
 */
inline fun <reified T : Event> EventListener.Companion.builder(): EventListener.Builder<T> = builder(T::class.java)

/**
 * Creates a new event listener that listens for events of the given event
 * type [T], running the given [handler] when an event of the correct type is
 * fired.
 *
 * @param T The type of events the listener will listen for.
 * @param handler The handler for the listener.
 *
 * @return A new event listener.
 */
inline fun <reified T : Event> EventListener.Companion.of(crossinline handler: (T) -> Unit): EventListener<T> {
    return of(T::class.java) { handler(it) }
}
