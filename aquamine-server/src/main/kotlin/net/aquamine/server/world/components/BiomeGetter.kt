package net.aquamine.server.world.components

import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.biome.Biome
import net.aquamine.api.world.biome.BiomeContainer
import net.aquamine.server.coordinate.QuartPos
import net.aquamine.server.world.biome.BiomeManager
import net.aquamine.server.world.biome.NoiseBiomeSource

interface BiomeGetter : ChunkGetter, BiomeContainer, NoiseBiomeSource {

    val biomeManager: BiomeManager

    fun getUncachedNoiseBiome(x: Int, y: Int, z: Int): Biome

    override fun getNoiseBiome(x: Int, y: Int, z: Int): Biome {
        val chunk = getChunk(QuartPos.toSection(x), QuartPos.toSection(z), false) ?: return getUncachedNoiseBiome(x, y, z)
        return chunk.getNoiseBiome(x, y, z)
    }

    override fun getBiome(x: Int, y: Int, z: Int): Biome = biomeManager.getBiome(x, y, z)

    override fun getBiome(position: Vec3i): Biome = getBiome(position.x, position.y, position.z)
}
