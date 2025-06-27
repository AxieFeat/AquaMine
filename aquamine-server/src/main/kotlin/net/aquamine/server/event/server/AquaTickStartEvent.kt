package net.aquamine.server.event.server

import net.aquamine.api.event.server.TickStartEvent

class AquaTickStartEvent(override val tickNumber: Int) : TickStartEvent
