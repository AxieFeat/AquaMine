package net.aquamine.server.ticking

/**
 * Something that can be processed by a tick thread, or ticked.
 *
 * This is designed to separate parts of the server that should be ticked with
 * the scaling requirements that the tick thread dispatcher can provide from
 * the main server tick loop that is ran by the tick scheduler.
 */
interface Tickable {

    fun tick(time: Long)
}
