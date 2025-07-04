package net.aquamine.api.event.server

import net.aquamine.api.event.Event

/**
 * An event that is called when the server starts or ends a tick.
 *
 * These events are called incredibly frequent. On a server with a normal
 * tick speed, these events will be called 20 times per second.
 */
sealed interface TickEvent : Event {

    /**
     * The number of the tick that has started. This will start from 0, which
     * will be the first tick, and increment by 1 for every completed tick
     * while the server is running.
     *
     * This is NOT a persisted value. It only counts up when the server is
     * running. When the server is restarted, this will reset to 0.
     */
    val tickNumber: Int
}
