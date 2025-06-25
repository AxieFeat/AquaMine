package net.aquamine.server.util

import net.aquamine.api.util.Position

object Worlds {

    /**
     * Determines whether the given position is both inside the maximum spawnable
     * height **and** inside the maximum world bounds.
     */
    @JvmStatic
    fun isInSpawnableBounds(pos: Position): Boolean = !isOutsideSpawnableHeight(pos.y) && isInHorizontalWorldBounds(pos)

    @JvmStatic
    private fun isOutsideSpawnableHeight(y: Double): Boolean = y < -20000000 || y >= 20000000

    @JvmStatic
    private fun isInHorizontalWorldBounds(pos: Position): Boolean {
        return pos.x >= -30000000 && pos.z >= -30000000 && pos.x < 30000000 && pos.z < 30000000
    }
}
