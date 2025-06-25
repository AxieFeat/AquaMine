package net.aquamine.server.entity.serializer.projectile

import net.aquamine.server.entity.projectile.KryptonTrident
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.item.KryptonItemStack
import xyz.axie.nbt.CompoundTag

object TridentSerializer : EntitySerializer<KryptonTrident> {

    private const val DEALT_DAMAGE_TAG = "DealtDamage"
    private const val TRIDENT_TAG = "Trident"

    override fun load(entity: KryptonTrident, data: CompoundTag) {
        ArrowLikeSerializer.load(entity, data)
        entity.dealtDamage = data.getBoolean(DEALT_DAMAGE_TAG)
        if (data.contains(TRIDENT_TAG, CompoundTag.ID)) entity.item = KryptonItemStack.from(data.getCompound(TRIDENT_TAG))
    }

    override fun save(entity: KryptonTrident): CompoundTag.Builder = ArrowLikeSerializer.save(entity).apply {
        putBoolean(DEALT_DAMAGE_TAG, entity.dealtDamage)
        put(TRIDENT_TAG, entity.item.save())
    }
}
