package net.aquamine.api.command

import net.kyori.adventure.audience.Audience
import net.aquamine.api.Server
import net.aquamine.api.permission.Subject

/**
 * A sender is an interface representing the sender of a command.
 */
interface Sender : Audience, Subject {

    /**
     * The name of the sender.
     *
     * How this is defined is entirely dependent on the subtype.
     */
    val name: String

    /**
     * The server that the sender is on.
     */
    val server: Server
}
