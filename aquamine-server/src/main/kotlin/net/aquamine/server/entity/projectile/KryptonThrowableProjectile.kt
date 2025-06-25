package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.ThrowableProjectile
import net.aquamine.api.item.ItemStack
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.ThrowableProjectileSerializer
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.world.KryptonWorld

abstract class KryptonThrowableProjectile(world: KryptonWorld) : KryptonProjectile(world), ThrowableProjectile {

    final override val serializer: EntitySerializer<KryptonThrowableProjectile>
        get() = ThrowableProjectileSerializer

    protected abstract fun defaultItem(): KryptonItemStack

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.ThrowableProjectile.ITEM, KryptonItemStack.EMPTY)
    }

    final override fun asItem(): ItemStack {
        val item = data.get(MetadataKeys.ThrowableProjectile.ITEM)
        if (item.isEmpty()) return defaultItem()
        return item
    }
}
