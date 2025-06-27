package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.LlamaSpit
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.world.AquaWorld

class AquaLlamaSpit(world: AquaWorld) : AquaProjectile(world), LlamaSpit {

    override val type: AquaEntityType<AquaLlamaSpit>
        get() = AquaEntityTypes.LLAMA_SPIT
}
