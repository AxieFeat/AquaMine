package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.WitherSkull
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.world.AquaWorld

class AquaWitherSkull(world: AquaWorld) : AquaAcceleratingProjectile(world), WitherSkull {

    override val type: AquaEntityType<AquaWitherSkull>
        get() = AquaEntityTypes.WITHER_SKULL

    override var isDangerous: Boolean
        get() = data.get(MetadataKeys.WitherSkull.DANGEROUS)
        set(value) = data.set(MetadataKeys.WitherSkull.DANGEROUS, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.WitherSkull.DANGEROUS, false)
    }
}
