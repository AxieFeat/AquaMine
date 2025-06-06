package net.aquamine.api.event.server

import net.aquamine.api.event.annotation.PerformanceSensitive
import net.aquamine.api.event.server.TickEvent

/**
 * Called when a tick begins.
 */
@PerformanceSensitive
interface TickStartEvent : TickEvent
