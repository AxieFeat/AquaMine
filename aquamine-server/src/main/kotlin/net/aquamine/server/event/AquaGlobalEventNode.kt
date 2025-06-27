package net.aquamine.server.event

import net.aquamine.api.event.Event
import net.aquamine.api.event.EventFilter
import net.aquamine.api.event.GlobalEventNode

object AquaGlobalEventNode : AquaEventNode<Event>("global", EventFilter.ALL, null), GlobalEventNode
