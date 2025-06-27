package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.ThrowableProjectile
import net.aquamine.api.item.ItemStack
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.ThrowableProjectileSerializer
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.world.AquaWorld

abstract class AquaThrowableProjectile(world: AquaWorld) : AquaProjectile(world), ThrowableProjectile {

    final override val serializer: EntitySerializer<AquaThrowableProjectile>
        get() = ThrowableProjectileSerializer

    protected abstract fun defaultItem(): AquaItemStack

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.ThrowableProjectile.ITEM, AquaItemStack.EMPTY)
    }

    final override fun asItem(): ItemStack {
        val item = data.get(MetadataKeys.ThrowableProjectile.ITEM)
        if (item.isEmpty()) return defaultItem()
        return item
    }
}
