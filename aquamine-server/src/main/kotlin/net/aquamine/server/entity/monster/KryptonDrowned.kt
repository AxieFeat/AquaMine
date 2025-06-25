package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Drowned
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.world.KryptonWorld

class KryptonDrowned(world: KryptonWorld) : KryptonZombie(world), Drowned {

    override val type: KryptonEntityType<KryptonDrowned>
        get() = KryptonEntityTypes.DROWNED

    override fun isPushedByFluid(): Boolean = !isSwimming
}
