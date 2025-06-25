package net.aquamine.server.entity.system

import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.packet.out.play.PacketOutSetCenterChunk
import net.aquamine.server.packet.out.play.PacketOutUnloadChunk
import net.aquamine.server.coordinate.ChunkPos
import net.aquamine.server.util.ChunkUtil
import java.util.concurrent.CompletableFuture

class PlayerChunkViewingSystem(private val player: KryptonPlayer) {

    private var previousCenter = ChunkPos.ZERO
    private val chunkAdder = ChunkUtil.ChunkPosConsumer { x, z ->
        val chunk = player.world.chunkManager.getChunk(x, z) ?: return@ChunkPosConsumer
        player.connection.write(chunk.cachedPacket)
    }
    private val chunkRemover = ChunkUtil.ChunkPosConsumer { x, z ->
        player.connection.send(PacketOutUnloadChunk(x, z))
    }

    fun loadInitialChunks() {
        val center = ChunkPos.forEntityPosition(player.position)
        previousCenter = center

        val range = player.server.config.world.viewDistance
        player.world.chunkManager.updateEntityPosition(player, center)

        CompletableFuture.runAsync {
            player.connection.send(PacketOutSetCenterChunk(center.x, center.z))
            ChunkUtil.forChunksInRange(center.x, center.z, range) { x, z ->
                val pos = ChunkPos(x, z)
                val chunk = player.world.chunkManager.loadChunk(pos) ?: return@forChunksInRange
                player.connection.write(chunk.cachedPacket)
            }
        }
    }

    fun updateChunks() {
        val oldCenter = previousCenter
        val newCenter = ChunkPos.forEntityPosition(player.position)
        previousCenter = newCenter

        val range = player.server.config.world.viewDistance
        player.connection.send(PacketOutSetCenterChunk(newCenter.x, newCenter.z))
        player.world.chunkManager.updatePlayerPosition(player, oldCenter, newCenter, range)
        ChunkUtil.forDifferingChunksInRange(newCenter.x, newCenter.z, oldCenter.x, oldCenter.z, range, chunkAdder, chunkRemover)
    }
}
