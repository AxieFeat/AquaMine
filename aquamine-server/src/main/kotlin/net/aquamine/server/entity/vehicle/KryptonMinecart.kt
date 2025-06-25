package net.aquamine.server.entity.vehicle

import net.aquamine.api.entity.vehicle.Minecart
import net.aquamine.api.entity.vehicle.MinecartVariant
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.world.KryptonWorld

class KryptonMinecart(world: KryptonWorld) : KryptonMinecartLike(world), Minecart {

    override val type: KryptonEntityType<KryptonMinecart>
        get() = KryptonEntityTypes.MINECART

    override val variant: MinecartVariant
        get() = MinecartVariant.RIDEABLE
}
