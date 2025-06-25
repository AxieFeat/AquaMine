package net.aquamine.server.event.server

import net.aquamine.api.event.server.ServerStartEvent

object KryptonServerStartEvent : ServerStartEvent {

    override fun toString(): String = "KryptonServerStartEvent"
}
