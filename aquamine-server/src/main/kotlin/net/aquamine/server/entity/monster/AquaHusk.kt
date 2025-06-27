package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Husk
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.world.AquaWorld

class AquaHusk(world: AquaWorld) : AquaZombie(world), Husk {

    override val type: AquaEntityType<AquaHusk>
        get() = AquaEntityTypes.HUSK
}
