package net.aquamine.api.event.annotation

/**
 * A marker annotation that signals that an event is highly sensitive and
 * should be handled very quickly. If heavy processing needs to be done for one
 * of these events, it should be done asynchronously.
 *
 * These events could be sensitive for a number of reasons. For example, an
 * event like [net.aquamine.api.event.player.PlayerMoveEvent] is sensitive because
 * it is fired very frequently, even if the server only has a couple of
 * players. Tick start and tick end events are also called a lot, and your
 * processing could severely limit the TPS capability of the server.
 */
annotation class PerformanceSensitive
