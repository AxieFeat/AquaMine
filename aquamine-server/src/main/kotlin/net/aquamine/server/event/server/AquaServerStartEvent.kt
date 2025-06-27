package net.aquamine.server.event.server

import net.aquamine.api.event.server.ServerStartEvent

object AquaServerStartEvent : ServerStartEvent {

    override fun toString(): String = "AquaServerStartEvent"
}
