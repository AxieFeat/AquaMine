package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.LlamaSpit
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.world.KryptonWorld

class KryptonLlamaSpit(world: KryptonWorld) : KryptonProjectile(world), LlamaSpit {

    override val type: KryptonEntityType<KryptonLlamaSpit>
        get() = KryptonEntityTypes.LLAMA_SPIT
}
