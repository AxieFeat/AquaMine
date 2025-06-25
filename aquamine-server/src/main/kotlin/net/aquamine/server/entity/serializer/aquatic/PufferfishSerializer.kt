package net.aquamine.server.entity.serializer.aquatic

import net.aquamine.server.entity.aquatic.KryptonPufferfish
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag

object PufferfishSerializer : EntitySerializer<KryptonPufferfish> {

    private const val PUFF_STATE_TAG = "PuffState"

    override fun load(entity: KryptonPufferfish, data: CompoundTag) {
        FishSerializer.load(entity, data)
        entity.data.set(MetadataKeys.Pufferfish.PUFF_STATE, data.getInt(PUFF_STATE_TAG))
    }

    override fun save(entity: KryptonPufferfish): CompoundTag.Builder = FishSerializer.save(entity).apply {
        putInt(PUFF_STATE_TAG, entity.data.get(MetadataKeys.Pufferfish.PUFF_STATE))
    }
}
