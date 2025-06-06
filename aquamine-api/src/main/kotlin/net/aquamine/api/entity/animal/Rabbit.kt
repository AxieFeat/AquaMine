package net.aquamine.api.entity.animal

import net.aquamine.api.entity.animal.type.RabbitVariant

/**
 * A rabbit.
 */
interface Rabbit : Animal {

    /**
     * The type of this rabbit.
     */
    var variant: RabbitVariant
}
