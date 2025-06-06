package net.aquamine.api

import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.text.Component

/**
 * The basic configuration options associated with the server.
 */
@ImmutableType
interface ServerConfig {

    /**
     * If the server is in online mode, meaning it authenticates players
     * through verified means. The authentication provider will, for most
     * implementations, be Mojang.
     */
    val isOnline: Boolean

    /**
     * The address that the server is bound to.
     */
    val ip: String

    /**
     * The port that the server is bound to.
     */
    val port: Int

    /**
     * The message of the day for the server's status.
     */
    val motd: Component

    /**
     * The maximum players that may join the server.
     */
    val maxPlayers: Int
}
