package net.aquamine.server.command.arguments.coordinates

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.StringReader
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType
import net.aquamine.server.command.arguments.CommandExceptions

/**
 * Various exceptions that may be thrown as a result of trying to parse
 * coordinates.
 */
object CoordinateExceptions {

    /**
     * Thrown when a user fails to input a value for a coordinate.
     *
     * This is only used by local coordinates, as they are the only coordinates
     * that require values for all 3 coordinates to be inputted.
     *
     * For example, the following are invalid and will throw this exception:
     * - `^ ^ ^`
     * - `^3 ^ ^4`
     * - `^6 ^2 ^`
     */
    @JvmField
    val POSITION_EXPECTED_DOUBLE: SimpleCommandExceptionType = CommandExceptions.simple("argument.pos.missing.double")

    /**
     * Thrown when a user fails to input all 3 coordinates that are required.
     *
     * For example, the following are invalid and will throw this exception:
     * - `^3 ^2`
     * - `~4 ~`
     * - `6 4`
     */
    @JvmField
    val POSITION_3D_INCOMPLETE: SimpleCommandExceptionType = CommandExceptions.simple("argument.pos3d.incomplete")

    /**
     * Thrown when a user inputs coordinates of different types in to the same
     * string.
     *
     * For example, the following are invalid and will throw this exception:
     * - `^3 ~4 8`
     * - `4 ~2 9`
     * - `~6 ^4 0`
     */
    @JvmField
    val POSITION_MIXED_TYPE: SimpleCommandExceptionType = CommandExceptions.simple("argument.pos.mixed")

    @JvmStatic
    fun checkPositionComplete(reader: StringReader, resetPosition: Int) {
        if (!reader.canRead() || reader.peek() != CommandDispatcher.ARGUMENT_SEPARATOR_CHAR) {
            CommandExceptions.resetAndThrow(reader, resetPosition, POSITION_3D_INCOMPLETE.createWithContext(reader))
        }
        reader.skip()
    }
}
