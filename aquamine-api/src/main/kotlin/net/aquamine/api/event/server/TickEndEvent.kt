package net.aquamine.api.event.server

import net.aquamine.api.event.annotation.PerformanceSensitive

/**
 * Called when a tick ends.
 */
@PerformanceSensitive
interface TickEndEvent : TickEvent {

    /**
     * The approximate duration of the tick, in nanoseconds.
     */
    val tickDuration: Long

    /**
     * The approximate time when the tick finished processing, in nanoseconds.
     */
    val endTime: Long
}
