package net.aquamine.server.command.arguments.coordinates

import net.aquamine.api.util.Position
import net.aquamine.server.command.CommandSourceStack

/**
 * A wrapper around all 3 types of coordinates that may be used in Minecraft.
 */
sealed interface Coordinates {

    /**
     * Calculates the new absolute position based on the coordinates held by
     * this object.
     *
     * This is where all the magic happens, in terms of actual movement
     * calculation.
     */
    fun calculatePosition(source: CommandSourceStack): Position
}
