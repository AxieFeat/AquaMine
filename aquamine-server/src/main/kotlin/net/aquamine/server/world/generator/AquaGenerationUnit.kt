package net.aquamine.server.world.generator

import net.aquamine.api.util.BoundingBox
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.generator.GenerationUnit
import net.aquamine.api.world.generator.UnitModifier
import net.aquamine.server.world.AquaWorld
import java.util.function.Consumer

// TODO: Add checks that the area is a multiple of 16
class AquaGenerationUnit(
    val world: AquaWorld,
    override val area: BoundingBox = ALL_WORLD
) : GenerationUnit {

    override val modifier: AquaUnitModifier = AquaUnitModifier(world, ALL_WORLD)

    override fun fork(box: BoundingBox): AquaGenerationUnit {
        return AquaGenerationUnit(world, box)
    }

    override fun fork(consumer: Consumer<UnitModifier>) {
        // TODO
    }

    companion object {
        @JvmField
        val ALL_WORLD = BoundingBox(
            Vec3i(-30000000, -64, -30000000),
            Vec3i(30000000, 319, 30000000),
        )
    }
}
