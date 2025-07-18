package net.aquamine.server.event.server

import net.aquamine.api.event.server.TickEndEvent

class AquaTickEndEvent(
    override val tickNumber: Int,
    override val tickDurationNanos: Long,
    override val tickDurationMillis: Long,
    override val endTime: Long
) : TickEndEvent
