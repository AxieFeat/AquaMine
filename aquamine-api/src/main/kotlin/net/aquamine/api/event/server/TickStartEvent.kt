package net.aquamine.api.event.server

import net.aquamine.api.event.annotation.PerformanceSensitive

/**
 * Called when a tick begins.
 */
@PerformanceSensitive
interface TickStartEvent : TickEvent
