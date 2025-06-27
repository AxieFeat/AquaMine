package net.aquamine.server.entity.serializer.aquatic

import net.aquamine.server.entity.aquatic.AquaFish
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.MobSerializer
import xyz.axie.nbt.CompoundTag

object FishSerializer : EntitySerializer<AquaFish> {

    private const val FROM_BUCKET_TAG = "FromBucket"

    override fun load(entity: AquaFish, data: CompoundTag) {
        MobSerializer.load(entity, data)
        entity.setSpawnedFromBucket(data.getBoolean(FROM_BUCKET_TAG))
    }

    override fun save(entity: AquaFish): CompoundTag.Builder = MobSerializer.save(entity).apply {
        putBoolean(FROM_BUCKET_TAG, entity.wasSpawnedFromBucket())
    }
}
