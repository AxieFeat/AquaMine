package net.aquamine.server.world.components

import net.aquamine.server.world.dimension.AquaDimensionType

interface ReadOnlyWorld : ChunkGetter, BlockGetter, BiomeGetter, BrightnessGetter {

    val dimensionType: AquaDimensionType

    override fun height(): Int = dimensionType.height

    override fun minimumBuildHeight(): Int = dimensionType.minimumY
}
