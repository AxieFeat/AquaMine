package net.aquamine.server.event.server

import net.aquamine.api.event.server.TickEndEvent

class KryptonTickEndEvent(override val tickNumber: Int, override val tickDuration: Long, override val endTime: Long) : TickEndEvent
