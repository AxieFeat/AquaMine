package net.aquamine.server.server

import net.aquamine.server.world.chunk.ChunkLoader
import net.aquamine.server.world.data.PlayerDataSerializer
import net.aquamine.server.world.data.WorldDataSerializer

/**
 * Classes required for the creation of the server.
 *
 * This exists to avoid having many constructor parameters in KryptonServer.
 */
@JvmRecord
data class InitContext(
    val statisticsSerializer: StatisticsSerializer,
    val worldDataSerializer: WorldDataSerializer,
    val playerDataSerializer: PlayerDataSerializer,
    val chunkLoader: ChunkLoader
)
