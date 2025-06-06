package net.aquamine.api.world.biome

import net.aquamine.api.util.Vec3i

/**
 * Something that contains biomes.
 *
 * The default value that will be returned instead of null if no biome is
 * found is [Biomes.PLAINS].
 */
interface BiomeContainer {

    /**
     * Gets the biome at the given [x], [y], and [z] coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     *
     * @return The biome.
     */
    fun getBiome(x: Int, y: Int, z: Int): Biome

    /**
     * Gets the biome at the given [position].
     *
     * @param position The position.
     *
     * @return The biome at the given position.
     */
    fun getBiome(position: Vec3i): Biome
}
