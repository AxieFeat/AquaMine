package net.aquamine.server.entity.serializer.projectile

import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.projectile.KryptonFireball
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.item.KryptonItemStack
import xyz.axie.nbt.CompoundTag

object FireballSerializer : EntitySerializer<KryptonFireball> {

    private const val ITEM_TAG = "Item"

    override fun load(entity: KryptonFireball, data: CompoundTag) {
        AcceleratingProjectileSerializer.load(entity, data)
        entity.data.set(MetadataKeys.Fireball.ITEM, KryptonItemStack.from(data.getCompound(ITEM_TAG)))
    }

    override fun save(entity: KryptonFireball): CompoundTag.Builder = AcceleratingProjectileSerializer.save(entity).apply {
        val item = entity.data.get(MetadataKeys.Fireball.ITEM)
        if (!item.isEmpty()) put(ITEM_TAG, item.save())
    }
}
