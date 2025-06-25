package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.WitherSkull
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.world.KryptonWorld

class KryptonWitherSkull(world: KryptonWorld) : KryptonAcceleratingProjectile(world), WitherSkull {

    override val type: KryptonEntityType<KryptonWitherSkull>
        get() = KryptonEntityTypes.WITHER_SKULL

    override var isDangerous: Boolean
        get() = data.get(MetadataKeys.WitherSkull.DANGEROUS)
        set(value) = data.set(MetadataKeys.WitherSkull.DANGEROUS, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.WitherSkull.DANGEROUS, false)
    }
}
