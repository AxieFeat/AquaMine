package net.aquamine.server.entity.vehicle

import net.aquamine.api.entity.vehicle.Minecart
import net.aquamine.api.entity.vehicle.MinecartVariant
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.world.AquaWorld

class AquaMinecart(world: AquaWorld) : AquaMinecartLike(world), Minecart {

    override val type: AquaEntityType<AquaMinecart>
        get() = AquaEntityTypes.MINECART

    override val variant: MinecartVariant
        get() = MinecartVariant.RIDEABLE
}
