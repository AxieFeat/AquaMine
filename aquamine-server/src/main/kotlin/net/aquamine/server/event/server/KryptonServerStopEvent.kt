package net.aquamine.server.event.server

import net.aquamine.api.event.server.ServerStopEvent

object KryptonServerStopEvent : ServerStopEvent {

    override fun toString(): String = "KryptonServerStopEvent"
}
