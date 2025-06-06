package net.aquamine.api.user

import net.aquamine.api.auth.GameProfile
import java.time.Instant

/**
 * Common data associated with both users and players.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface BaseUser {

    /**
     * The cached game profile associated with this user.
     */
    val profile: GameProfile

    /**
     * If this user has joined this server before.
     */
    @get:JvmName("hasJoinedBefore")
    val hasJoinedBefore: Boolean

    /**
     * The time that this user first joined the server.
     */
    val firstJoined: Instant

    /**
     * The latest time when this user last joined the server.
     */
    val lastJoined: Instant

    /**
     * If this user is online or not.
     *
     * @return `true` if this user is online.
     */
    fun isOnline(): Boolean
}
