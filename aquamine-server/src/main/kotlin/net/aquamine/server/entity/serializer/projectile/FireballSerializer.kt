package net.aquamine.server.entity.serializer.projectile

import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.projectile.AquaFireball
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.item.AquaItemStack
import xyz.axie.nbt.CompoundTag

object FireballSerializer : EntitySerializer<AquaFireball> {

    private const val ITEM_TAG = "Item"

    override fun load(entity: AquaFireball, data: CompoundTag) {
        AcceleratingProjectileSerializer.load(entity, data)
        entity.data.set(MetadataKeys.Fireball.ITEM, AquaItemStack.from(data.getCompound(ITEM_TAG)))
    }

    override fun save(entity: AquaFireball): CompoundTag.Builder = AcceleratingProjectileSerializer.save(entity).apply {
        val item = entity.data.get(MetadataKeys.Fireball.ITEM)
        if (!item.isEmpty()) put(ITEM_TAG, item.save())
    }
}
