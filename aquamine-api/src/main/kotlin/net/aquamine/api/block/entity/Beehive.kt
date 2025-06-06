package net.aquamine.api.block.entity

import net.aquamine.api.entity.animal.Bee
import net.aquamine.api.util.Vec3i

/**
 * A beehive.
 */
interface Beehive : EntityStorageBlockEntity<Bee> {

    /**
     * The position of a flower that one of the bees has found, so that other
     * bees in the beehive can find it.
     */
    var flower: Vec3i?

    /**
     * Whether this beehive is sedated due to a campfire underneath it.
     *
     * @return `true` if this beehive is sedated.
     */
    fun isSedated(): Boolean
}
