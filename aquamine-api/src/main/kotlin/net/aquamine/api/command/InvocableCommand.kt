package net.aquamine.api.command

/**
 * A command that may be invoked with arbitrary arguments.
 *
 * @param A the type of the arguments.
 */
sealed interface InvocableCommand<A> : Command {

    /**
     * Invokes this command with the given [sender] and [args].
     *
     * This will be called by the command manager when this command is invoked.
     *
     * A command is defined as being invoked when a [Sender] executes it
     * through some medium, such as typing it out in chat, or in the console.
     *
     * @param sender The sender who ran this command.
     * @param args The arguments provided by the sender.
     */
    fun execute(sender: Sender, args: A)

    /**
     * Gets the list of suggestions for the given [sender] and the given
     * [args].
     *
     * This will be called by the command manager when suggestions are
     * requested for this command.
     *
     * @param sender The sender who sent the tab completion request.
     * @param args The arguments provided by the sender.
     *
     * @return A list of possible tab completions.
     */
    fun suggest(sender: Sender, args: A): List<String> = listOf()

    /**
     * Checks if the given [sender] has permission to execute this command with
     * the given [args].
     *
     * @param sender The sender attempting to execute the command.
     * @param args The arguments provided by the sender.
     *
     * @return `true` if the sender can perform this command with the arguments,
     * `false` otherwise.
     */
    fun hasPermission(sender: Sender, args: A): Boolean = true
}
