package net.aquamine.api.block.entity

import net.aquamine.api.auth.GameProfile

/**
 * A skull.
 */
interface Skull : BlockEntity {

    /**
     * The owner of the skull.
     *
     * This determines what head is rendered on the skull.
     */
    var owner: GameProfile?
}
