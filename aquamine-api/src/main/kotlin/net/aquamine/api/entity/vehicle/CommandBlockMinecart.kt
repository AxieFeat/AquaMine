package net.aquamine.api.entity.vehicle

import net.kyori.adventure.text.Component

/**
 * A minecart with a command block in it.
 */
interface CommandBlockMinecart : MinecartLike {

    /**
     * The command that this command block Minecart will run.
     */
    var command: String

    /**
     * The last output from the command block Minecart running the command.
     */
    var lastOutput: Component
}
