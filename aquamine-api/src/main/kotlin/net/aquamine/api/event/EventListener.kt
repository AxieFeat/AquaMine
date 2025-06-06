package net.aquamine.api.event

import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import net.aquamine.api.AquaMine
import java.util.function.Consumer
import java.util.function.Predicate

/**
 * A listener (handler) for an event type.
 */
interface EventListener<T : Event> {

    /**
     * The type of events this listener listens for.
     */
    val eventType: Class<T>

    /**
     * Runs this listener with the given [event], and returns the result.
     *
     * @param event The event to run the listener on.
     *
     * @return The result.
     */
    fun run(event: T): Result

    /**
     * The result of running an event listener.
     */
    enum class Result {

        /**
         * Indicates that the listener ran successfully, without errors or
         * issues.
         */
        SUCCESS,

        /**
         * Indicates that the passed event was invalid for the listener, such
         * as if the event failed to pass one of the filters, or if the event
         * was denied and the listener does not ignore denials.<
         */
        INVALID,

        /**
         * Indicates that the listener has expired, which will happen either
         * when the listener reaches the expiry count, if set, or when the
         * expiry when condition is met, if set.
         */
        EXPIRED,

        /**
         * Indicates that the listener threw an exception while executing.
         */
        EXCEPTION
    }

    /**
     * A builder for an event listener.
     */
    interface Builder<T : Event> {

        /**
         * Adds the given [filter] to the list of filters that will be checked
         * when an event is passed to the listener.
         *
         * There can be multiple filters for a listener, and **all** of them
         * must pass for the event to be considered valid and passed on to the
         * listener.
         *
         * @param filter The filter to add.
         *
         * @return This builder.
         */
        fun addFilter(filter: Predicate<T>): Builder<T>

        /**
         * Sets whether the listener should ignore events that have
         * been denied.
         *
         * @param ignore Whether to ignore denied events.
         *
         * @return This builder.
         */
        fun ignoreDenied(ignore: Boolean): Builder<T>

        /**
         * Sets the number of times the listener can be run before it is
         * considered expired and will not run anymore.
         *
         * @param count The number of times the listener can be run before it
         * expires.
         * @return This builder.
         */
        fun expireCount(count: Int): Builder<T>

        /**
         * Sets the condition that, if met, will cause the listener to be
         * considered expired and not have it run anymore.
         *
         * @param condition The expiry condition.
         *
         * @return This builder.
         */
        fun expireWhen(condition: Predicate<T>): Builder<T>

        /**
         * Sets the handler for the listener.
         *
         * This is what will be called by the event system if the event passes
         * all the set conditions (filters, expiry, etc.).
         *
         * @param handler The handler for the listener.
         *
         * @return This builder.
         */
        fun handler(handler: Consumer<T>): Builder<T>

        /**
         * Builds the event listener from this builder.
         *
         * @return The built event listener.
         */
        fun build(): EventListener<T>
    }

    @TypeFactory
    @ApiStatus.Internal
    interface Factory {

        fun <T : Event> builder(eventType: Class<T>): Builder<T>
    }

    companion object {

        /**
         * Creates a new event listener builder for events of the
         * given [eventType].
         *
         * @param T The type of event.
         * @param eventType The type of events the listener will listen for.
         *
         * @return A new builder.
         */
        @JvmStatic
        fun <T : Event> builder(eventType: Class<T>): Builder<T> = AquaMine.factory<Factory>().builder(eventType)

        /**
         * Creates a new event listener that listens for events of the
         * given [eventType], running the given [handler] when an event of the
         * correct type is fired.
         *
         * @param T The type of event.
         * @param eventType The type of events the listener will listen for.
         * @param handler The handler for the listener.
         *
         * @return A new event listener.
         */
        @JvmStatic
        fun <T : Event> of(eventType: Class<T>, handler: Consumer<T>): EventListener<T> = builder(eventType).handler(handler).build()
    }
}
