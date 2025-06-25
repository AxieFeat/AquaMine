package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Husk
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.world.KryptonWorld

class KryptonHusk(world: KryptonWorld) : KryptonZombie(world), Husk {

    override val type: KryptonEntityType<KryptonHusk>
        get() = KryptonEntityTypes.HUSK
}
