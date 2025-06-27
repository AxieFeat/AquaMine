package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.AquaAxolotl
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag

object AxolotlSerializer : EntitySerializer<AquaAxolotl> {

    private const val VARIANT_TAG = "Variant"
    private const val FROM_BUCKET_TAG = "FromBucket"

    override fun load(entity: AquaAxolotl, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        entity.data.set(MetadataKeys.Axolotl.VARIANT, data.getInt(VARIANT_TAG))
        entity.setSpawnedFromBucket(data.getBoolean(FROM_BUCKET_TAG))
    }

    override fun save(entity: AquaAxolotl): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putInt(VARIANT_TAG, entity.data.get(MetadataKeys.Axolotl.VARIANT))
        putBoolean(FROM_BUCKET_TAG, entity.wasSpawnedFromBucket())
    }
}
