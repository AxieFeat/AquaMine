package net.aquamine.api.event.command

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.command.Sender
import net.aquamine.api.event.type.DeniableEventWithResult

/**
 * Called when any sender executes a command.
 */
interface CommandExecuteEvent : DeniableEventWithResult<CommandExecuteEvent.Result> {

    /**
     * The sender that executed the command.
     */
    val sender: Sender

    /**
     * The command that was executed.
     */
    val command: String

    /**
     * The result of a command's execution.
     *
     * This can be used to completely replace the command that the player
     * executed, which can be useful for redirecting commands to others.
     *
     * @property command The replacement command to execute.
     */
    @JvmRecord
    @ImmutableType
    data class Result(val command: String)
}
