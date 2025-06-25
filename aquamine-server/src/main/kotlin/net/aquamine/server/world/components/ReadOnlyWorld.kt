package net.aquamine.server.world.components

import net.aquamine.server.world.dimension.KryptonDimensionType

interface ReadOnlyWorld : ChunkGetter, BlockGetter, BiomeGetter, BrightnessGetter {

    val dimensionType: KryptonDimensionType

    override fun height(): Int = dimensionType.height

    override fun minimumBuildHeight(): Int = dimensionType.minimumY
}
