package net.aquamine.server.event.server

import net.aquamine.api.event.server.ServerStopEvent

object AquaServerStopEvent : ServerStopEvent {

    override fun toString(): String = "AquaServerStopEvent"
}
