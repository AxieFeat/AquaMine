package net.aquamine.server.event.entity

import net.aquamine.api.entity.Entity
import net.aquamine.api.event.entity.RemoveEntityEvent
import net.aquamine.api.event.type.AbstractDeniableEvent
import net.aquamine.api.world.World

class KryptonRemoveEntityEvent(override val entity: Entity, override val world: World) : AbstractDeniableEvent(), RemoveEntityEvent
