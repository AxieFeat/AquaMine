package net.aquamine.server.entity.serializer.projectile

import net.aquamine.server.entity.projectile.AquaProjectile
import net.aquamine.server.entity.serializer.BaseEntitySerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.getUUID
import net.aquamine.server.util.nbt.hasUUID
import net.aquamine.server.util.nbt.putNullable
import net.aquamine.server.util.nbt.putUUID
import xyz.axie.nbt.CompoundTag

object ProjectileSerializer : EntitySerializer<AquaProjectile> {

    private const val OWNER_TAG = "Owner"
    private const val LEFT_OWNER_TAG = "LeftOwner"
    private const val HAS_BEEN_SHOT_TAG = "HasBeenShot"

    override fun load(entity: AquaProjectile, data: CompoundTag) {
        BaseEntitySerializer.load(entity, data)
        if (data.hasUUID(OWNER_TAG)) entity.setOwnerId(data.getUUID(OWNER_TAG))
        entity.setLeftOwner(data.getBoolean(LEFT_OWNER_TAG))
        entity.setBeenShot(data.getBoolean(HAS_BEEN_SHOT_TAG))
    }

    override fun save(entity: AquaProjectile): CompoundTag.Builder = BaseEntitySerializer.save(entity).apply {
        putNullable(OWNER_TAG, entity.ownerId(), CompoundTag.Builder::putUUID)
        if (entity.hasLeftOwner()) putBoolean(LEFT_OWNER_TAG, true)
        putBoolean(HAS_BEEN_SHOT_TAG, entity.hasBeenShot())
    }
}
