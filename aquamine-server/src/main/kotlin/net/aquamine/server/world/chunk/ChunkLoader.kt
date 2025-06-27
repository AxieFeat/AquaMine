package net.aquamine.server.world.chunk

import net.aquamine.server.coordinate.ChunkPos
import net.aquamine.server.world.AquaWorld

interface ChunkLoader : AutoCloseable {

    fun loadChunk(world: AquaWorld, pos: ChunkPos): AquaChunk?

    fun loadAllEntities(chunk: AquaChunk)

    fun saveChunk(chunk: AquaChunk)

    fun saveAllEntities(chunk: AquaChunk)

    override fun close() {
        // Do nothing by default - optional, only if required
    }
}
