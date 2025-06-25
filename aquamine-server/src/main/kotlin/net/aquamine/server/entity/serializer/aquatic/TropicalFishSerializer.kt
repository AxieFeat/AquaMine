package net.aquamine.server.entity.serializer.aquatic

import net.aquamine.server.entity.aquatic.KryptonTropicalFish
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag

object TropicalFishSerializer : EntitySerializer<KryptonTropicalFish> {

    private const val VARIANT_TAG = "Variant"

    override fun load(entity: KryptonTropicalFish, data: CompoundTag) {
        FishSerializer.load(entity, data)
        entity.data.set(MetadataKeys.TropicalFish.VARIANT, data.getInt(VARIANT_TAG))
    }

    override fun save(entity: KryptonTropicalFish): CompoundTag.Builder = FishSerializer.save(entity).apply {
        putInt(VARIANT_TAG, entity.data.get(MetadataKeys.TropicalFish.VARIANT))
    }
}
