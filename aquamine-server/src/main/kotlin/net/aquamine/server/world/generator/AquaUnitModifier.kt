package net.aquamine.server.world.generator

import net.aquamine.api.block.BlockState
import net.aquamine.api.util.BoundingBox
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.biome.Biome
import net.aquamine.api.world.chunk.BlockChangeFlags
import net.aquamine.api.world.generator.UnitModifier
import net.aquamine.server.world.AquaWorld

// TODO: Add checks for out of bounds of area.
class AquaUnitModifier(
    val world: AquaWorld,
    val area: BoundingBox,
) : UnitModifier {

    override fun setBlock(x: Int, y: Int, z: Int, block: BlockState) {
        world.setBlock(x, y, z, block, BlockChangeFlags.all())
    }

    override fun fill(block: BlockState) {
        fillArea(area, block)
    }

    override fun fill(area: BoundingBox, block: BlockState) {
        fillArea(area, block)
    }

    override fun fillHeight(minHeight: Int, maxHeight: Int, block: BlockState) {
        for (y in minHeight..maxHeight) {
            for(x in area.minX.toInt()..area.maxX.toInt()) {
                for (z in area.minZ.toInt()..area.maxZ.toInt()) {
                    setBlock(x, y, z, block)
                }
            }
        }
    }

    override fun fillBiome(biome: Biome) {
        // TODO
    }

    override fun getBlock(x: Int, y: Int, z: Int): BlockState = world.getBlock(x, y, z)

    override fun getBlock(position: Vec3i): BlockState = world.getBlock(position)

    private fun fillArea(area: BoundingBox, block: BlockState) {
        for(x in area.minX.toInt()..area.maxX.toInt()) {
            for(y in area.minY.toInt()..area.maxY.toInt()) {
                for(z in area.minZ.toInt()..area.maxZ.toInt()) {
                    setBlock(x, y, z, block)
                }
            }
        }
    }
}