package net.aquamine.server.command.arguments.coordinates

import com.mojang.brigadier.StringReader
import net.aquamine.api.util.Position
import net.aquamine.server.command.CommandSourceStack

@JvmRecord
data class WorldCoordinates(val x: WorldCoordinate, val y: WorldCoordinate, val z: WorldCoordinate) : Coordinates {

    override fun calculatePosition(source: CommandSourceStack): Position {
        return Position(x.get(source.position.x), y.get(source.position.y), z.get(source.position.z))
    }

    companion object {

        @JvmStatic
        fun parse(reader: StringReader, correctCenter: Boolean): WorldCoordinates {
            val position = reader.cursor
            val x = WorldCoordinate.parse(reader, correctCenter)
            CoordinateExceptions.checkPositionComplete(reader, position)
            val y = WorldCoordinate.parse(reader, false)
            CoordinateExceptions.checkPositionComplete(reader, position)
            val z = WorldCoordinate.parse(reader, correctCenter)
            return WorldCoordinates(x, y, z)
        }
    }
}
