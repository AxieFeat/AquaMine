package net.aquamine.api.event.player

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.event.type.DeniableEventWithResult

/**
 * An event that is called when a request is made to authenticate a player with
 * the given [username].
 */
interface PlayerAuthenticationEvent : DeniableEventWithResult<PlayerAuthenticationEvent.Result> {

    /**
     * The username of the player that is being authenticated.
     */
    val username: String

    /**
     * The result of a request to authenticate a player.
     *
     * This allows for the authenticated profile to be provided to the server,
     * to allow plugins to replace the authentication process with their own.
     *
     * @property profile The replacement authenticated profile to use.
     */
    @JvmRecord
    @ImmutableType
    data class Result(val profile: GameProfile)
}
