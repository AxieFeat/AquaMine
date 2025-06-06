package net.aquamine.api.command

import net.aquamine.api.entity.player.Player

/**
 * The command manager is responsible for registering, unregistering, and
 * keeping track of commands.
 */
interface CommandManager {

    /**
     * Registers the given Brigadier [command] with the given [meta] to this
     * manager.
     *
     * @param command The command to register.
     * @param meta The command metadata.
     */
    fun register(command: BrigadierCommand, meta: CommandMeta)

    /**
     * Registers the given invocable [command] with the given [meta] to this
     * manager.
     *
     * @param command The command to register.
     * @param meta The command metadata.
     */
    fun register(command: InvocableCommand<*>, meta: CommandMeta)

    /**
     * Unregisters the given alias from this manager, if registered.
     *
     * @param alias The alias to unregister.
     */
    fun unregister(alias: String)

    /**
     * Dispatches a command from the given [sender] with the given [command]
     * as the input.
     *
     * @param sender The sender of the command.
     * @param command The command to dispatch.
     *
     * @return `true` if the command dispatched successfully, `false` otherwise.
     */
    fun dispatch(sender: Sender, command: String): Boolean

    /**
     * Updates the list of known commands for the given [player].
     *
     * This is useful for permission plugins that want to trigger a refresh of
     * commands when a user gains or loses a permission.
     *
     * @param player The player to update commands for.
     */
    fun updateCommands(player: Player)
}
