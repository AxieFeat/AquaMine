package net.aquamine.spark

import me.lucko.spark.common.tick.AbstractTickReporter
import net.aquamine.api.Server
import net.aquamine.api.event.Event
import net.aquamine.api.event.EventNode
import net.aquamine.api.event.EventNode.Companion.all
import net.aquamine.api.event.server.TickEndEvent
import java.util.*

class AquaTickReporter(
    val server: Server,
) : AbstractTickReporter() {

    private val node: EventNode<Event> = all("sparkTickReporter-" + UUID.randomUUID()).also {
        it.registerListener(TickEndEvent::class.java) { event ->
            onTick(event.tickDurationMillis.toDouble())
        }
    }

    override fun start() {
        server.eventNode.addChild(node)
    }

    override fun close() {
        server.eventNode.removeChild(node)
    }
}