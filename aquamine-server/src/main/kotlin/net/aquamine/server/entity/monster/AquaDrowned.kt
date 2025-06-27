package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Drowned
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.world.AquaWorld

class AquaDrowned(world: AquaWorld) : AquaZombie(world), Drowned {

    override val type: AquaEntityType<AquaDrowned>
        get() = AquaEntityTypes.DROWNED

    override fun isPushedByFluid(): Boolean = !isSwimming
}
