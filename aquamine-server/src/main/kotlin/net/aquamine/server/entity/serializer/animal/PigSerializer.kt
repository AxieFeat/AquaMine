package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.KryptonPig
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag

object PigSerializer : EntitySerializer<KryptonPig> {

    private const val SADDLE_TAG = "Saddle"

    override fun load(entity: KryptonPig, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        entity.isSaddled = data.getBoolean(SADDLE_TAG)
    }

    override fun save(entity: KryptonPig): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putBoolean(SADDLE_TAG, entity.isSaddled)
    }
}
