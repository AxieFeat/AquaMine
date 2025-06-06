package net.aquamine.api.block.entity

import net.kyori.adventure.text.Component

/**
 * A command block.
 */
interface CommandBlock : BlockEntity {

    /**
     * The command that this command block will run.
     */
    var command: String

    /**
     * The last output from the command block running the command.
     */
    var lastOutput: Component

    /**
     * Whether this command block is powered.
     *
     * @return `true` if this command block is powered.
     */
    fun isPowered(): Boolean

    /**
     * Whether this command block executes automatically, not requiring a
     * redstone signal.
     *
     * @return `true` if this command block executes automatically.
     */
    fun isAutomatic(): Boolean

    /**
     * Executes this command block.
     */
    fun execute()
}
