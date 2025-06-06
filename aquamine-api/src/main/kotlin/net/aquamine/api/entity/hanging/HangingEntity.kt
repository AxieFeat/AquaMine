package net.aquamine.api.entity.hanging

import net.aquamine.api.entity.Entity
import net.aquamine.api.util.Direction

/**
 * An entity that hangs from something, usually on one of the horizontal faces
 * of a block.
 */
interface HangingEntity : Entity {

    /**
     * The direction this hanging entity is facing.
     */
    var direction: Direction
}
