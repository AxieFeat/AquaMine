package net.aquamine.api.user

import net.aquamine.api.entity.player.Player
import java.util.UUID

/**
 * A user. This is a player that can be offline, and mostly exists for that
 * purpose.
 */
interface User : BaseUser {

    /**
     * The name of this user.
     */
    val name: String

    /**
     * The UUID of this user.
     */
    val uuid: UUID

    /**
     * Gets the player associated with this user, or null if they are not
     * currently available, usually meaning they are offline.
     *
     * @return The associated player.
     */
    fun asPlayer(): Player?
}
