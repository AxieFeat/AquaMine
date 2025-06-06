package net.aquamine.api.event.player

import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.text.Component
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.event.type.DeniableEventWithResult
import java.net.InetSocketAddress

/**
 * Called when a player has been authenticated, but they have not yet had
 * a player object constructed for them.
 */
interface PlayerLoginEvent : DeniableEventWithResult<PlayerLoginEvent.Result> {

    /**
     * The game profile of the player logging in.
     */
    val profile: GameProfile

    /**
     * The address that the player is logging in from.
     */
    val address: InetSocketAddress

    /**
     * The result of a login event.
     *
     * This allows plugins to specify a reason for a player to be kicked.
     *
     * @property reason The reason for the player to be kicked.
     */
    @JvmRecord
    @ImmutableType
    data class Result(val reason: Component)
}
