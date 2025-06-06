package net.aquamine.api.command

import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.text.Component
import net.aquamine.api.Server
import net.aquamine.api.entity.player.Player
import net.aquamine.api.util.Position
import net.aquamine.api.world.World

/**
 * The context of a command execution.
 */
@ImmutableType
interface CommandExecutionContext {

    /**
     * The sender of the command.
     */
    val sender: Sender

    /**
     * The position of the sender.
     */
    val position: Position

    /**
     * The plain text name of the sender.
     */
    val textName: String

    /**
     * The display name of the sender.
     */
    val displayName: Component

    /**
     * The world the sender is in.
     */
    val world: World

    /**
     * The server the sender is on.
     */
    val server: Server

    /**
     * Checks if the sender is a player entity.
     *
     * If this returns true, [asPlayer] is guaranteed to return non-null.
     *
     * @return true if the sender is a player
     */
    fun isPlayer(): Boolean

    /**
     * Gets the sender of the command as a player.
     *
     * @return the sender as a player
     */
    fun asPlayer(): Player?

    /**
     * Sends the given [message] as a success message to the sender.
     *
     * @param message the message to send
     */
    fun sendSuccessMessage(message: Component)

    /**
     * Sends the given [message] as a failure message to the sender.
     *
     * @param message the message to send
     */
    fun sendFailureMessage(message: Component)
}
