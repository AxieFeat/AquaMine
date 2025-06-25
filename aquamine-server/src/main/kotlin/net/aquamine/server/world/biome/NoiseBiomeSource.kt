package net.aquamine.server.world.biome

import net.aquamine.api.world.biome.Biome

fun interface NoiseBiomeSource {

    fun getNoiseBiome(x: Int, y: Int, z: Int): Biome
}
