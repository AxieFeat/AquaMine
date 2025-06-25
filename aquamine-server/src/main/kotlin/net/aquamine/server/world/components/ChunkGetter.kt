package net.aquamine.server.world.components

import net.aquamine.api.util.Vec3i
import net.aquamine.server.coordinate.SectionPos
import net.aquamine.server.world.chunk.KryptonChunk

interface ChunkGetter : HeightAccessor {

    fun hasChunk(x: Int, z: Int): Boolean

    fun hasChunkAt(x: Int, z: Int): Boolean = hasChunk(SectionPos.blockToSection(x), SectionPos.blockToSection(z))

    fun hasChunkAt(pos: Vec3i): Boolean = hasChunkAt(pos.x, pos.z)

    fun hasChunksAt(from: Vec3i, to: Vec3i): Boolean = hasChunksAt(from.x, from.y, from.z, to.x, to.y, to.z)

    fun hasChunksAt(x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int): Boolean =
        if (y2 >= minimumBuildHeight() && y1 < maximumBuildHeight()) hasChunksAt(x1, z1, x2, z2) else false

    fun hasChunksAt(x1: Int, z1: Int, x2: Int, z2: Int): Boolean {
        val fromX = SectionPos.blockToSection(x1)
        val toX = SectionPos.blockToSection(x2)
        val fromZ = SectionPos.blockToSection(z1)
        val toZ = SectionPos.blockToSection(z2)
        for (x in fromX..toX) {
            for (z in fromZ..toZ) {
                if (!hasChunk(x, z)) return false
            }
        }
        return true
    }

    fun getChunk(x: Int, z: Int, shouldCreate: Boolean): KryptonChunk?

    fun getChunk(x: Int, z: Int): KryptonChunk?
}
