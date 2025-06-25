package net.aquamine.server.entity.serializer.projectile

import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.projectile.KryptonThrowableProjectile
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.item.KryptonItemStack
import xyz.axie.nbt.CompoundTag

object ThrowableProjectileSerializer : EntitySerializer<KryptonThrowableProjectile> {

    private const val ITEM_TAG = "Item"

    override fun load(entity: KryptonThrowableProjectile, data: CompoundTag) {
        ProjectileSerializer.load(entity, data)
        entity.data.set(MetadataKeys.ThrowableProjectile.ITEM, KryptonItemStack.from(data.getCompound(ITEM_TAG)))
    }

    override fun save(entity: KryptonThrowableProjectile): CompoundTag.Builder = ProjectileSerializer.save(entity).apply {
        val item = entity.data.get(MetadataKeys.ThrowableProjectile.ITEM)
        if (!item.isEmpty()) put(ITEM_TAG, item.save())
    }
}
