package net.aquamine.spark.tick

import me.lucko.spark.common.tick.AbstractTickReporter
import net.aquamine.api.Server
import net.aquamine.api.event.EventFilter
import net.aquamine.api.event.EventNode
import net.aquamine.api.event.EventNode.Companion.forType
import net.aquamine.api.event.server.TickEndEvent

class AquaTickReporter(
    val server: Server
) : AbstractTickReporter() {

    private val node: EventNode<TickEndEvent> = forType(
        "sparkTickReporter",
        EventFilter.create<TickEndEvent, Any>(TickEndEvent::class.java)
    ).also {
        it.registerListener(TickEndEvent::class.java) { event ->
            onTick(event.tickDuration / 1000000.0)
        }
    }

    override fun start() {
        server.eventNode.addChild(node)
    }

    override fun close() {
        server.eventNode.removeChild(node)
    }
}
