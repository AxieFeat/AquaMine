package net.aquamine.server.world.chunk

import net.aquamine.server.coordinate.ChunkPos
import net.aquamine.server.world.KryptonWorld

interface ChunkLoader : AutoCloseable {

    fun loadChunk(world: KryptonWorld, pos: ChunkPos): KryptonChunk?

    fun loadAllEntities(chunk: KryptonChunk)

    fun saveChunk(chunk: KryptonChunk)

    fun saveAllEntities(chunk: KryptonChunk)

    override fun close() {
        // Do nothing by default - optional, only if required
    }
}
